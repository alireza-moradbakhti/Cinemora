package arya.projects.data.repository

import android.util.Log
import arya.projects.core.common.ErrorType
import arya.projects.core.common.Resource
import arya.projects.data.BuildConfig
import arya.projects.data.local.MovieDao
import arya.projects.data.mapper.MovieMapper.toDomain
import arya.projects.data.mapper.MovieMapper.toEntity
import arya.projects.data.remote.MovieApiService
import arya.projects.domain.entity.Movie
import arya.projects.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Concrete implementation of the MovieRepository interface.
 * Coordinates data from local and remote sources.
 */
@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val apiService: MovieApiService,
    private val movieDao: MovieDao
) : MovieRepository {

    // --- Fetch Popular Movies (Network-Bound Resource Pattern) ---
    override fun getPopularMovies(): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        val cacheType = "popular"

        // 1. Load from cache immediately
        val cachedFlow = movieDao.getMoviesByType(cacheType)
            .map { entities -> Resource.Loading(entities.map { it.toDomain() }) }

        emitAll(cachedFlow.catch {
            Log.e("MovieRepository", "Error reading cache: ${it.message}")
        })

        try {
            // 2. Fetch from network
            val response = apiService.getPopularMovies(apiKey = BuildConfig.TMDB_API_KEY)

            // 3. Update cache
            val entities = response.results.map { it.toEntity(cacheType) }
            movieDao.insertMovies(entities)

            // The cachedFlow (step 1) will automatically emit the new data now
        } catch (e: HttpException) {
            emit(Resource.Error(ErrorType.ApiError(e.code(), e.message())))
        } catch (e: IOException) {
            // Network connection error
            emit(Resource.Error(ErrorType.NetworkError))
        } catch (e: Exception) {
            emit(Resource.Error(ErrorType.UnknownError))
        }
    }

    // --- Fetch Favorite Movies ---
    override fun getFavoriteMovies(): Flow<Resource<List<Movie>>> = flow {
        // Since Room provides a Flow, we can wrap it and use catch in the outer flow
        emit(Resource.Loading())
        try {
            // Emit the Room flow wrapped in Resource.Success
            movieDao.getFavoriteMovies().collect { entities ->
                emit(Resource.Success(entities.map { it.toDomain() }))
            }
        } catch (e: Exception) {
            Log.e("MovieRepository", "Error fetching favorite movies: ${e.message}")
            emit(Resource.Error(ErrorType.DatabaseError))
        }
    }

    // --- Toggle Favorite Status ---
    override suspend fun toggleFavoriteStatus(movieId: Int, isFavorite: Boolean): Resource<Unit> {
        return try {
            val movieEntity = movieDao.getMovieById(movieId)
            if (movieEntity != null) {
                movieDao.updateMovie(movieEntity.copy(isFavorite = isFavorite))
                Resource.Success(Unit)
            } else {
                // If the movie isn't in the DB, it can't be favorite yet (edge case)
                Resource.Error(ErrorType.DatabaseError, null)
            }
        } catch (e: Exception) {
            Resource.Error(ErrorType.DatabaseError, null)
        }
    }
}
package arya.projects.domain.repository

import arya.projects.core.common.Resource
import arya.projects.domain.entity.Movie
import kotlinx.coroutines.flow.Flow

/**
 * Defines the contract for accessing Movie data.
 * This interface abstracts the data sources (e.g., network, local database)
 * and is implemented in the data layer.
 *
 * It provides methods to fetch movies and manage their favorite status.
 */
interface MovieRepository {

    fun getPopularMovies(): Flow<Resource<List<Movie>>>

    fun getFavoriteMovies(): Flow<Resource<List<Movie>>>

    suspend fun toggleFavoriteStatus(movieId: Int, isFavorite: Boolean): Resource<Unit>
}
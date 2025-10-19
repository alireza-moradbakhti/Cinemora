package arya.projects.domain.usecase

import arya.projects.core.common.Resource
import arya.projects.domain.entity.Movie
import arya.projects.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case to retrieve a flow of popular movies, including loading and error states.
 */
class GetPopularMoviesUseCase(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<Resource<List<Movie>>> {
        return repository.getPopularMovies()
    }
}

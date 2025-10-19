package arya.projects.domain.usecase

import arya.projects.core.common.Resource
import arya.projects.domain.repository.MovieRepository

/**
 * Use case to switch a movie's favorite status.
 */
class ToggleFavoriteMovieUseCase (
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int, isFavorite: Boolean): Resource<Unit> {
        return repository.toggleFavoriteStatus(movieId, isFavorite)
    }
}
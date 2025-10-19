package arya.projects.feature_movie.list.util

import arya.projects.domain.entity.Movie


/**
 * Data class representing the current state of the Movie List UI.
 * This state will be consumed by the Composable.
 *
 * @param movies The list of movies to display.
 * @param isLoading True if data is actively being fetched.
 * @param errorMessage A user-facing message to display if an error occurred.
 * @param endReached True if the end of the list has been reached.
 * @param page The current page number for pagination.
 */
data class MovieListState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val isFavorite: Boolean = false,
    val errorMessage: String? = null,
    val endReached: Boolean = false,
    val page: Int = 1,
)

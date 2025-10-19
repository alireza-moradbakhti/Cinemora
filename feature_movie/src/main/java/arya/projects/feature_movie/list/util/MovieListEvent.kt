package arya.projects.feature_movie.list.util

/**
 * Sealed class representing one-shot UI events triggered by the ViewModel.
 * These are used for actions that should happen once and not persist across configuration changes,
 * such as navigation, showing a Snack bar, or triggering an animation.
 */
sealed class MovieListEvent {
    /**
     * Event to navigate to the detailed view of a specific movie.
     * @param movieId The ID of the movie to display.
     */
    data class NavigateToDetail(val movieId: Int) : MovieListEvent()

    /**
     * Example: Event to show a brief, temporary message (e.g., when a favorite action is successful).
     */
    data class ShowMessage(val message: String) : MovieListEvent()

    // Add other one-shot events here (e.g., NavigateToSearch, OpenFilterSheet, etc.)
}

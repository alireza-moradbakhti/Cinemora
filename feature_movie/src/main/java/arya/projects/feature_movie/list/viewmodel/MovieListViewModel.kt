package arya.projects.feature_movie.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arya.projects.core.common.Resource
import arya.projects.core.common.ResourceProvider
import arya.projects.domain.usecase.GetPopularMoviesUseCase
import arya.projects.domain.usecase.ToggleFavoriteMovieUseCase
import arya.projects.feature_movie.list.util.MovieListEvent
import arya.projects.feature_movie.list.util.MovieListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getPapularMoviesUseCase: GetPopularMoviesUseCase,
    private val toggleFavoriteMovieUseCase: ToggleFavoriteMovieUseCase,
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    private val _state = MutableStateFlow(MovieListState())
    val state = _state.asStateFlow()


    // --- One-Shot Event Handling ---
    // Channel is used for one-shot events (like navigation) to ensure they are consumed only once.
    private val _eventChannel = Channel<MovieListEvent>(Channel.BUFFERED)
    val eventFlow = _eventChannel.receiveAsFlow()

    init {
        getMovies()
    }

    /**
     * Initiates the flow to fetch popular movies from the repository.
     * The collector handles Resource states (Loading, Success, Error).
     */
    fun getMovies() {
        getPapularMoviesUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    // Update state with loading status and potentially display partial data from cache
                    _state.value = _state.value.copy(
                        isLoading = true,
                        errorMessage = null,
                        movies = result.data ?: emptyList() // Show cached data if available
                    )
                }

                is Resource.Success -> {
                    // Update state with successful data
                    _state.value = _state.value.copy(
                        isLoading = false,
                        errorMessage = null,
                        movies = result.data ?: emptyList()
                    )
                }

                is Resource.Error -> {
                    // Use the ResourceProvider to translate the ErrorType into a user-facing string.
                    val message = resourceProvider.getErrorString(result.error)

                    // Update state with the error message
                    _state.value = _state.value.copy(
                        isLoading = false,
                        errorMessage = message,
                        // Keep old data to display alongside the error (helpful if the user loses connection)
                        movies = result.data ?: _state.value.movies
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    /**
     * Called when a movie card is clicked. Sends a navigation event to the UI via the event channel.
     * @param movieId The ID of the clicked movie.
     */
    fun onMovieClick(movieId: Int) {
        viewModelScope.launch {
            _eventChannel.send(MovieListEvent.NavigateToDetail(movieId))
        }
    }

    /**
     * Called when the favorite icon is clicked. Executes the ToggleFavorite use case.
     * @param movieId The ID of the movie to toggle favorite status for.
     * @param isCurrentlyFavorite The current favorite status of the movie.
     */
    fun onToggleFavoriteClick(movieId: Int, isCurrentlyFavorite: Boolean) {
        viewModelScope.launch {
            // Note: This operation requires the underlying ToggleFavoriteMovieUseCase
            // to now accept both the movieId and the new desired state (!isCurrentlyFavorite).
            val result = toggleFavoriteMovieUseCase(movieId, !isCurrentlyFavorite)

            if (result is Resource.Error) {
                // If the toggle operation itself fails, notify the user via ShowMessage event.
                val message = resourceProvider.getErrorString(result.error)
                _eventChannel.send(MovieListEvent.ShowMessage(message))
            }
        }
    }

}
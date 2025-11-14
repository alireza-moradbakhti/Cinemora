package arya.projects.feature_movie.list.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import arya.projects.core.ui.components.ErrorScreen
import arya.projects.core.ui.components.LoadingScreen
import arya.projects.core.ui.components.WelcomeScreen
import arya.projects.core.utils.AppConstants
import arya.projects.core.utils.safeNavigate
import arya.projects.core.utils.showMessage
import arya.projects.feature_movie.list.util.MovieListEvent
import arya.projects.feature_movie.list.viewmodel.MovieListViewModel

@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel = hiltViewModel(),
    navController: NavController,
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(viewModel.eventFlow) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is MovieListEvent.NavigateToDetail -> {
                    navController.safeNavigate(
                        route = AppConstants.MOVIE_DETAIL_ROUTE,
                    )
                }

                is MovieListEvent.ShowMessage -> {
                    showMessage(context, event.message)
                }
            }
        }
    }

    when {

        state.isLoading && state.movies == null -> LoadingScreen()

        state.errorMessage != null -> ErrorScreen(
            error = state.errorMessage.orEmpty(),
            onRetry = {
                viewModel.getMovies()
            }
        )

        state.movies != null -> MoviesScreen()

        else -> WelcomeScreen()
    }


}
package arya.projects.feature_movie.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import arya.projects.core.utils.AppConstants
import arya.projects.feature_movie.list.ui.MovieListScreen

fun NavGraphBuilder.movieNavGraph(navController: NavController) {

    navigation(
        startDestination = MovieRoutes.MovieList.route,
        route = "movie_graph"
    ) {
        composable(route = MovieRoutes.MovieList.route) {
            MovieListScreen(
                navController = navController
            )
        }

        composable(
            route = AppConstants.MOVIE_DETAIL_ROUTE,
            arguments = listOf(navArgument(AppConstants.MOVIE_ID_ARG) { type = NavType.StringType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString(AppConstants.MOVIE_ID_ARG)
//            MovieDetailScreen(movieId = movieId.orEmpty())
        }

        composable(route = MovieRoutes.FavouriteMovies.route) {
//            FavouriteMoviesScreen()
        }

    }
}
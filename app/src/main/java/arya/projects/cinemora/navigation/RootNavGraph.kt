package arya.projects.cinemora.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import arya.projects.feature_movie.navigation.MovieRoutes
import arya.projects.feature_movie.navigation.movieNavGraph

@Composable
fun RootNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = "movie_graph"
    ) {
        movieNavGraph(navController)
        //add more navGraph per features

    }


}
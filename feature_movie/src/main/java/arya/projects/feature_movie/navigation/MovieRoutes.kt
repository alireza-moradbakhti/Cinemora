package arya.projects.feature_movie.navigation

import arya.projects.core.utils.AppConstants

/**
 * Defines the navigation routes for the movie feature module.
 *
 * This class encapsulates all the route constants and their corresponding argument keys,
 * providing a centralized and type-safe way to navigate within the movie-related screens.
 */
sealed class MovieRoutes(val route: String) {

    data object MovieList : MovieRoutes(AppConstants.MOVIES_LIST_ROUTE)
    data object FavouriteMovies : MovieRoutes(AppConstants.FAVOURITES_ROUTE)
    data class MovieDetail(val movieId: String) : MovieRoutes("${AppConstants.MOVIE_DETAIL_BASE_ROUTE}/$movieId")

}
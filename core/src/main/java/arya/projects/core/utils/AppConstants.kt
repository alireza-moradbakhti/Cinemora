package arya.projects.core.utils

object AppConstants {

    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val TABLE_MOVIE = "movies"
    const val DATABASE_NAME = "cinemora_db"

    // Arguments
    const val MOVIE_ID_ARG = "movieId"
    const val ARG_MOVIE_TITLE = "movie_title"
    const val USER_ID_ARG = "userId"

    // Navigation Routes
    const val MOVIE_DETAIL_BASE_ROUTE = "movie_detail"

    const val MOVIE_DETAIL_ROUTE = "$MOVIE_DETAIL_BASE_ROUTE/{$MOVIE_ID_ARG}"
    const val MOVIES_LIST_ROUTE = "movies_list"
    const val FAVOURITES_ROUTE = "favourites"


}
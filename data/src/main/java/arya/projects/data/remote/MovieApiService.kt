package arya.projects.data.remote

import arya.projects.data.remote.model.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        // API key will be added via OkHttp Interceptor for security
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): MovieListDto

}
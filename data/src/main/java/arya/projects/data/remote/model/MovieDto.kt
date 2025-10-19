package arya.projects.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data Transfer Object (DTO) for receiving movie data from TheMovieDB API.
 * Uses Kotlinx Serialization annotations.
 */
@Serializable
data class MovieDto(
    val id: Int,
    val title: String,
    val overview: String,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("release_date")
    val releaseDate: String?,
    @SerialName("vote_average")
    val voteAverage: Double,
)

@Serializable
data class MovieListDto(
    @SerialName("results")
    val results: List<MovieDto>
)

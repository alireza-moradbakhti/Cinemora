package arya.projects.data.mapper

import arya.projects.data.local.model.MovieEntity
import arya.projects.data.remote.model.MovieDto
import arya.projects.domain.entity.Movie

/**
 * Object responsible for converting Data layer models (DTOs, Entities) into Domain Entities.
 */
object MovieMapper {

    // --- DTO to Domain Entity ---
    fun MovieDto.toDomain(isFavorite: Boolean = false): Movie {
        return Movie(
            id = id,
            title = title,
            overview = overview,
            posterPath = posterPath,
            releaseDate = releaseDate,
            voteAverage = voteAverage,
            isFavorite = isFavorite
        )
    }

    // --- Local Entity to Domain Entity ---
    fun MovieEntity.toDomain(): Movie {
        return Movie(
            id = id,
            title = title,
            overview = overview,
            posterPath = posterPath,
            releaseDate = releaseDate,
            voteAverage = voteAverage,
            isFavorite = isFavorite
        )
    }

    // --- DTO to Local Entity (for caching) ---
    fun MovieDto.toEntity(type: String): MovieEntity {
        return MovieEntity(
            id = id,
            title = title,
            overview = overview,
            posterPath = posterPath,
            releaseDate = releaseDate,
            voteAverage = voteAverage,
            isFavorite = false, // Initial favorite status is false when fetched from API
            type = type
        )
    }


}
package arya.projects.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import arya.projects.core.utils.AppConstants

/**
 * Room Entity for storing movie data in the local database cache.
 */
@Entity(tableName = AppConstants.TABLE_MOVIE)
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val releaseDate: String?,
    val voteAverage: Double,
    val isFavorite: Boolean = false,
    val type: String // E.g., "popular", "trending", used for caching strategy
)
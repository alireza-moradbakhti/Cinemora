package arya.projects.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import arya.projects.data.local.model.MovieEntity

/**
 * The Room Database configuration for the app.
 */
@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}

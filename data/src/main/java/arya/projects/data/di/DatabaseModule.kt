package arya.projects.data.di

import android.content.Context
import androidx.room.Room
import arya.projects.core.utils.AppConstants
import arya.projects.data.local.MovieDao
import arya.projects.data.local.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for providing local persistence dependencies (Room Database).
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase =
        Room.databaseBuilder(
                context,
                MovieDatabase::class.java,
                AppConstants.DATABASE_NAME
            ).fallbackToDestructiveMigration(false)
            .build()


    @Provides
    @Singleton
    fun provideMovieDao(database: MovieDatabase): MovieDao {
        return database.movieDao()
    }
}
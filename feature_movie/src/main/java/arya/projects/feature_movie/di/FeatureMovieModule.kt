package arya.projects.feature_movie.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Hilt module scoped to the ViewModel for the feature_movie.
 * Any dependencies specific only to this feature's ViewModels would be declared here.
 */
@Module
@InstallIn(ViewModelComponent::class)
object FeatureMovieModule {
    // Example: If you needed a MoviePreferencesHelper specific to this feature, it would be provided here.
}
package arya.projects.cinemora.di

import arya.projects.domain.repository.MovieRepository
import arya.projects.domain.usecase.GetPopularMoviesUseCase
import arya.projects.domain.usecase.ToggleFavoriteMovieUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for explicitly providing Use Cases from the Domain layer.
 * This pattern keeps the Domain module (and the Use Case classes) free of Hilt annotations,
 * enforcing its status as a pure Kotlin library, while still allowing injection.
 */
@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideGetPopularMoviesUseCase(
        repository: MovieRepository
    ): GetPopularMoviesUseCase {
        // Since GetPopularMoviesUseCase no longer has @Inject constructor,
        // we construct it here manually.
        return GetPopularMoviesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideToggleFavoriteMovieUseCase(
        repository: MovieRepository
    ): ToggleFavoriteMovieUseCase {
        // Since ToggleFavoriteMovieUseCase no longer has @Inject constructor,
        // we construct it here manually.
        return ToggleFavoriteMovieUseCase(repository)
    }
}
package arya.projects.domain.usecase

data class MovieUseCases(
    val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    val toggleFavoriteMovieUseCase: ToggleFavoriteMovieUseCase
)

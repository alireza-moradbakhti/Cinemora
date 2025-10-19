package arya.projects.core.common

/**
 * Defines standard error types for handling in the Presentation layer.
 */
sealed class ErrorType {
    data object NetworkError : ErrorType()
    data object DatabaseError : ErrorType()
    data object UnknownError : ErrorType()
    data class ApiError(val code: Int, val message: String) : ErrorType()
}
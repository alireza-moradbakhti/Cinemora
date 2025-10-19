package arya.projects.core.common

/**
 * A generic sealed class that represents a resource with its loading status.
 * It is designed to encapsulate data along with its state, which can be one of
 * [Success], [Error], or [Loading].
 *
 * This class is particularly useful for managing data that comes from asynchronous
 * operations, such as network requests or database queries, in a structured and
 * type-safe way.
 *
 * @param T The type of the data held by the resource.
 * @property data The data payload of the resource. It can be null, especially during [Loading] or [Error] states.
 * @property error The error information, present only in the [Error] state.
 */
sealed class Resource <T>(val data: T? = null, val error: ErrorType? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(error: ErrorType, data: T? = null) : Resource<T>(data, error)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}
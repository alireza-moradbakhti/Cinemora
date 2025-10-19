package arya.projects.core.common

import androidx.annotation.StringRes

/**
 * Interface to provide access to Android resources (like Strings)
 * in non-Android dependent layers (like Domain or ViewModel).
 * This allows the Use Cases and ViewModels to be unit tested on the JVM.
 */
interface ResourceProvider {
    /**
     * Retrieves a string resource by its ID.
     */
    fun getString(@StringRes resId: Int): String

    /**
     * Retrieves a string resource with formatting arguments.
     */
    fun getString(@StringRes resId: Int, vararg formatArgs: Any): String

    /**
     * Retrieves a user-friendly string message based on the generic ErrorType.
     * This decouples the ViewModel from knowing specific R.string IDs for errors.
     */
    fun getErrorString(errorType: ErrorType?): String

    // Add other resource access methods here as needed (e.g., getDimen, getColor)
}
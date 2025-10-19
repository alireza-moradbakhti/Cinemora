package arya.projects.cinemora.util

import android.content.Context
import androidx.annotation.StringRes
import arya.projects.cinemora.R
import arya.projects.core.common.ErrorType
import arya.projects.core.common.ResourceProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Concrete implementation of the ResourceProvider that uses the Application Context.
 * Must be installed in the :app module since it depends on the Android Context.
 */
@Singleton
class DefaultResourceProvider @Inject constructor(
    @ApplicationContext private val context: Context
) : ResourceProvider {

    override fun getString(@StringRes resId: Int): String {
        return context.getString(resId)
    }

    override fun getString(@StringRes resId: Int, vararg formatArgs: Any): String {
        return context.getString(resId, *formatArgs)
    }

    override fun getErrorString(errorType: ErrorType?): String {
        return when (errorType) {
            is ErrorType.NetworkError -> context.getString(R.string.error_network)
            is ErrorType.DatabaseError -> context.getString(R.string.error_database_generic)
            is ErrorType.ApiError -> {
                val code = errorType.code
                val message = errorType.message
                // Use a string with placeholders if you need to include the code/message
                context.getString(R.string.error_api_generic, code, message)
            }

            is ErrorType.UnknownError -> context.getString(R.string.error_unknown)

            else -> context.getString(R.string.error_generic)
        }
    }
}
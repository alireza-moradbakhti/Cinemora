package arya.projects.core.common

import android.content.Context

/**
 * An interface for providing a context, typically an Android `Context`.
 * This abstraction is used to decouple components from the Android framework,
 * allowing for easier testing and use in non-Android modules.
 */
interface ContextProvider {

    fun getContext() : Context

}
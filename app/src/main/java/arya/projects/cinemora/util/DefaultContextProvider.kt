package arya.projects.cinemora.util

import android.content.Context
import arya.projects.core.common.ContextProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Concrete implementation of the ContextProvider that uses the Application Context.
 * Must be installed in the :app module since it depends on the Android Context.
 */
@Singleton
class DefaultContextProvider @Inject constructor(
    @ApplicationContext private val context: Context
) : ContextProvider {

    override fun getContext(): Context = context
}
package arya.projects.cinemora.di

import arya.projects.cinemora.util.DefaultContextProvider
import arya.projects.core.common.ResourceProvider
import arya.projects.cinemora.util.DefaultResourceProvider
import arya.projects.core.common.ContextProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for binding the concrete ResourceProvider implementation.
 * This ensures that the core and domain layers can request ResourceProvider
 * without knowing the Android Context-dependent implementation details.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class ResourceModule {

    @Binds
    @Singleton
    abstract fun bindResourceProvider(
        defaultResourceProvider: DefaultResourceProvider
    ): ResourceProvider

    @Binds
    @Singleton
    abstract fun bindContextProvider(
        defaultContextProvider: DefaultContextProvider
    ): ContextProvider


}
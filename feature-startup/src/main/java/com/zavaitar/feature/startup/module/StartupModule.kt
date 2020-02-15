package com.zavaitar.feature.startup.module

import com.zavaitar.feature.startup.StartupManager
import com.zavaitar.feature.startup.StartupManagerImpl
import com.zavaitar.feature.startup.StartupUseCase
import com.zavaitar.feature.startup.StartupUseCaseImpl
import com.zavaitar.feature.startup.presentation.StartupFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class StartupModule {

    @ContributesAndroidInjector(modules = [StartupFragmentModule::class])
    internal abstract fun contributeStartupFragment(): StartupFragment

    @Binds
    internal abstract fun bindStartupUseCase(impl: StartupUseCaseImpl): StartupUseCase

    @Binds
    @Singleton
    internal abstract fun bindStartupManager(impl: StartupManagerImpl): StartupManager
}
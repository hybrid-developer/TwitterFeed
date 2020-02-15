package com.zavaitar.feature.feed.module

import com.zavaitar.feature.feed.presentation.FeedActivity
import com.zavaitar.feature.feed.presentation.FeedFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FeedModule {

    @ContributesAndroidInjector(modules = [FeedFragmentModule::class])
    internal abstract fun contributeFeedFragment(): FeedFragment

    @ContributesAndroidInjector
    internal abstract fun contributeFeedActivity(): FeedActivity
}
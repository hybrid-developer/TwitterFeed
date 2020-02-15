package com.zavaitar.feature.startup

import com.zavaitar.core.network.api.TwitterFeedApi
import com.zavaitar.feature.startup.model.TwitterFeed
import com.zavaitar.feature.startup.model.convertToFeed
import javax.inject.Inject
import javax.inject.Provider

interface StartupManager {
    suspend fun getTwitterFeed(): List<TwitterFeed>
}

internal class StartupManagerImpl @Inject constructor(
    private val feedApiProvider: Provider<TwitterFeedApi>
) : StartupManager {

    override suspend fun getTwitterFeed(): List<TwitterFeed> {
       return feedApiProvider.get().getTwitterFeed(
            LIST_ID, TWEET_MODE, INCLUDED_ENTITIES, COUNT
        ).map { items -> items.map { twitterFeedResponse -> twitterFeedResponse.convertToFeed() }
        }.blockingGet()
    }

    private companion object {
        private const val LIST_ID = 871746761387323394
        private const val TWEET_MODE = "extended"
        private const val INCLUDED_ENTITIES = 1
        private const val COUNT = 10
    }
}
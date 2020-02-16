package com.zavaitar.feature.feed

import androidx.annotation.VisibleForTesting
import com.zavaitar.core.network.api.TwitterFeedApi
import com.zavaitar.core.network.model.TwitterFeedResponse
import com.zavaitar.feature.feed.model.TwitterFeed
import com.zavaitar.feature.feed.model.convertToFeed
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Provider

interface FeedManager {
    suspend fun getTwitterFeed(): List<TwitterFeed>
}

internal class FeedManagerImpl @Inject constructor(
    private val feedApiProvider: Provider<TwitterFeedApi>
) : FeedManager {

    //This function was unit tested in FeedUseCase
    override suspend fun getTwitterFeed(): List<TwitterFeed> {
        return convertToTweeterFeed(
            feedApiProvider.get().getTwitterFeed(
                LIST_ID, TWEET_MODE, INCLUDED_ENTITIES, COUNT
            )
        )
    }

    @VisibleForTesting
    internal fun convertToTweeterFeed(response: List<TwitterFeedResponse>): List<TwitterFeed> {
        return Single.just(response)
            .map { items -> items.map { twitterFeedResponse -> twitterFeedResponse.convertToFeed() }
            }.blockingGet()
    }

    private companion object {
        private const val LIST_ID = 871746761387323394
        private const val TWEET_MODE = "extended"
        private const val INCLUDED_ENTITIES = 1
        private const val COUNT = 10
    }
}
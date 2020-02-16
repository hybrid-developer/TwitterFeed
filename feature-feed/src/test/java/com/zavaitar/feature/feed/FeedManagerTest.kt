package com.zavaitar.feature.feed

import com.nhaarman.mockitokotlin2.whenever
import com.zavaitar.core.network.api.TwitterFeedApi
import com.zavaitar.core.network.model.TwitterFeedResponse
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Provider
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@RunWith(MockitoJUnitRunner::class)
class FeedManagerTest {

    @Mock lateinit var twitterFeedApiProvider: Provider<TwitterFeedApi>

    @Mock lateinit var twitterFeedResponse: TwitterFeedResponse

    private lateinit var feedManager: FeedManagerImpl

    @Before
    fun setup() {
        feedManager = FeedManagerImpl(twitterFeedApiProvider)
    }

    @Test
    fun `convert twitter feed response to list of twitter feed`() {
        whenever(twitterFeedResponse.createdAt).thenReturn(CREATED_DATE)
        whenever(twitterFeedResponse.fullText).thenReturn(CONTENT)
        val originalList = listOf(twitterFeedResponse)

        val result = feedManager.convertToTweeterFeed(originalList)

        assertEquals(twitterFeedResponse.createdAt, result[0].createdDate)
        assertEquals(twitterFeedResponse.fullText, result[0].content)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `convert twitter feed and check with a null field return exception`() {
        whenever(twitterFeedResponse.createdAt).thenReturn(null)
        whenever(twitterFeedResponse.fullText).thenReturn(CONTENT)
        val originalList = listOf(twitterFeedResponse)

        val result = feedManager.convertToTweeterFeed(originalList)

        assertNotEquals(twitterFeedResponse.createdAt, result[0].createdDate)
        assertEquals(twitterFeedResponse.fullText, result[0].content)
    }

    private companion object {
        private const val CREATED_DATE = "Created Date"
        private const val CONTENT = "Content"
        private const val RANDOM_DATA = "Random data"
    }
}
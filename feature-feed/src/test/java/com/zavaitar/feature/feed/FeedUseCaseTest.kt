package com.zavaitar.feature.feed

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.zavaitar.core.network.NetworkState
import com.zavaitar.core.test.rxutils.coroutines.MainCoroutineRule
import com.zavaitar.core.viewmodel.Result
import com.zavaitar.feature.feed.model.TwitterFeed
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FeedUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock lateinit var feedManager: FeedManager

    @Mock lateinit var networkState: NetworkState

    private lateinit var feedUseCase: FeedUseCaseImpl

    @Before
    fun setup() {
        feedUseCase = FeedUseCaseImpl(networkState, feedManager)
    }

    @Test
    fun `return success result when you get the response as expected `() {
        val list = twitterFeedData()

        val result = feedUseCase.mapResults(list)

        assertTrue { result is Result.Success<List<TwitterFeed>> }
        assertEquals((result as Result.Success).data.size, 6)
    }

    @Test
    fun `return success result with an empty list when you get empty data`() {
        val list = emptyList<TwitterFeed>()

        val result = feedUseCase.mapResults(list)

        assertTrue { result is Result.Success<List<TwitterFeed>> }
        assertEquals((result as Result.Success).data.size, 0)
    }

    private fun twitterFeedData(): List<TwitterFeed> {
        val list = mutableListOf<TwitterFeed>()
        for (index in 0..5) {
            list.add(TwitterFeed("$CREATED_DATE$index", "$CONTENT$index"))
        }
        return list
    }

    private companion object {
        private const val CREATED_DATE = "Created Date"
        private const val CONTENT = "Content"
    }
}
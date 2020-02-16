package com.zavaitar.feature.feed.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.verify
import com.zavaitar.core.test.rxutils.coroutines.MainCoroutineRule
import com.zavaitar.core.test.rxutils.livedata.getData
import com.zavaitar.core.viewmodel.Result
import com.zavaitar.feature.feed.FeedUseCase
import com.zavaitar.feature.feed.R
import com.zavaitar.feature.feed.model.TwitterFeed
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlin.math.absoluteValue
import kotlin.test.*

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FeedViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock lateinit var feedUseCase: FeedUseCase

    @Mock lateinit var observerSingleEvent: Observer<Void>

    private lateinit var feedViewModel: FeedViewModel

    @Before
    fun setup() {
        feedViewModel = FeedViewModel(feedUseCase)
    }

    @Test
    fun `display list empty error message when loading twitter feed`() {
        feedUseCase.stub {
            on { runBlocking { getTwitterFeed() } } doReturn Result.Success(emptyList())
        }

        feedViewModel.dataLoadingStopEvent.observeForever(observerSingleEvent)
        mainCoroutineRule.pauseDispatcher()

        feedViewModel.loadTwitterFeed()

        verify(observerSingleEvent, never()).onChanged(null)

        mainCoroutineRule.resumeDispatcher()

        assertTrue(feedViewModel.errorMessageDisplayStatusEvent.getData())
        assertEquals(feedViewModel.errorScreenEvent.getData().absoluteValue,
            R.string.feed_data_not_received)
        verify(observerSingleEvent).onChanged(null)
    }

    @Test
    fun `display actual list when api returns the list full twitter feeds`() {

        feedUseCase.stub {
            on { runBlocking { getTwitterFeed() } } doReturn Result.Success(
                listOf(
                    TwitterFeed("created date", "content")
                )
            )
        }

        feedViewModel.dataLoadingStopEvent.observeForever(observerSingleEvent)
        mainCoroutineRule.pauseDispatcher()

        feedViewModel.loadTwitterFeed()

        verify(observerSingleEvent, never()).onChanged(null)

        mainCoroutineRule.resumeDispatcher()

        assertFalse(feedViewModel.errorMessageDisplayStatusEvent.getData())
        assertNull(feedViewModel.errorScreenEvent.getData())
        assertNotNull(feedViewModel.twitterFeedEvent.getData())

        verify(observerSingleEvent).onChanged(null)
    }

    @Test
    fun `display error screen when viewmodel receive a no network error`() {

        feedUseCase.stub {
            on { runBlocking { getTwitterFeed() } } doReturn Result.Error(Result.NO_NETWORK)
        }

        feedViewModel.dataLoadingStopEvent.observeForever(observerSingleEvent)
        mainCoroutineRule.pauseDispatcher()

        feedViewModel.loadTwitterFeed()

        verify(observerSingleEvent, never()).onChanged(null)

        mainCoroutineRule.resumeDispatcher()

        assertTrue(feedViewModel.errorMessageDisplayStatusEvent.getData())
        assertEquals(feedViewModel.errorScreenEvent.getData().absoluteValue,
            R.string.feed_network_error_message)

        verify(observerSingleEvent).onChanged(null)
    }

    @Test
    fun `display error screen when viewmodel receive an any other generic error`() {

        feedUseCase.stub {
            on { runBlocking { getTwitterFeed() } } doReturn Result.Error("Other Errors")
        }

        feedViewModel.dataLoadingStopEvent.observeForever(observerSingleEvent)
        mainCoroutineRule.pauseDispatcher()

        feedViewModel.loadTwitterFeed()

        verify(observerSingleEvent, never()).onChanged(null)

        mainCoroutineRule.resumeDispatcher()

        assertTrue(feedViewModel.errorMessageDisplayStatusEvent.getData())
        assertEquals(feedViewModel.errorScreenEvent.getData().absoluteValue,
            R.string.feed_other_error)

        verify(observerSingleEvent).onChanged(null)
    }
}
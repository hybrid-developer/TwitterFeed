package com.zavaitar.feature.feed.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zavaitar.core.viewmodel.Result
import com.zavaitar.core.viewmodel.SingleLiveEvent
import com.zavaitar.feature.feed.ErrorType
import com.zavaitar.feature.feed.FeedUseCase
import com.zavaitar.feature.feed.model.TwitterFeed
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class FeedViewModel @Inject constructor(
    private val feedUseCase: FeedUseCase
): ViewModel() {

    var twitterFeedEvent = SingleLiveEvent<List<TwitterFeed>>().apply { value = emptyList() }
    val errorScreenEvent = SingleLiveEvent<Int>()
    val dataLoadingStopEvent = SingleLiveEvent<Void>()
    val errorMessageDisplayStatusEvent = SingleLiveEvent<Boolean>()

    fun loadTwitterFeed() {
        viewModelScope.launch {
            val response = feedUseCase.getTwitterFeed()
            if (response is Result.Success) {
                if (response.data.isEmpty()) {
                    errorScreenEvent.value = ErrorType.EMPTY_DATA.errorMessage
                    errorMessageDisplayStatusEvent.value = true
                } else {
                    errorMessageDisplayStatusEvent.value = false
                    twitterFeedEvent.value = response.data
                }
            } else {
                if ((response as Result.Error).error.equals(Result.NO_NETWORK)){
                    errorScreenEvent.value = ErrorType.NETWORK_FAILED.errorMessage
                } else errorScreenEvent.value = ErrorType.OTHER.errorMessage

                errorMessageDisplayStatusEvent.value = true
                twitterFeedEvent.value = emptyList()
            }

            dataLoadingStopEvent.call()
        }
    }
}
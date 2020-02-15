package com.zavaitar.feature.startup.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zavaitar.core.viewmodel.Result
import com.zavaitar.core.viewmodel.Result.Success
import com.zavaitar.core.viewmodel.SingleLiveEvent
import com.zavaitar.feature.startup.StartupUseCase
import com.zavaitar.feature.startup.model.TwitterFeed
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class StartupViewModel @Inject constructor(
    private val startupUseCase: StartupUseCase
): ViewModel() {

    private val _twitterFeed = MutableLiveData<List<TwitterFeed>>().apply { value = emptyList() }
    val twittweFeed: LiveData<List<TwitterFeed>> = _twitterFeed

    val errorNoNetwork = SingleLiveEvent<Void>()

    fun loadTwitterFeed() {
        viewModelScope.launch {
            val response = startupUseCase.getTwitterFeed()
            if (response is Success) {
                _twitterFeed.value = response.data
                Log.d("Twitter Feed", response.data.toString())
            } else {
                _twitterFeed.value = emptyList()
                Log.d("Twitter Feed", "Twitter Feed Error")
            }
        }
    }
}

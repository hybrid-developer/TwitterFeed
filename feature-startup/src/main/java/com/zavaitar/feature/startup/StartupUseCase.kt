package com.zavaitar.feature.startup

import androidx.annotation.VisibleForTesting
import com.zavaitar.core.network.NetworkState
import com.zavaitar.core.viewmodel.Result
import com.zavaitar.feature.startup.model.TwitterFeed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface StartupUseCase {

    suspend fun getTwitterFeed(): Result<List<TwitterFeed>>
}

internal class StartupUseCaseImpl @Inject constructor(
    private val networkState: NetworkState,
    private val startupManager: StartupManager
) : StartupUseCase {

    override suspend fun getTwitterFeed(): Result<List<TwitterFeed>> = withContext(Dispatchers.IO){
      //  whenConnected {
            mapResults(startupManager.getTwitterFeed())
      //  }
    }

    @VisibleForTesting
    internal fun mapResults(response: List<TwitterFeed>) : Result<List<TwitterFeed>> {
        return try {
            Result.Success(response)
        } catch (ex: Exception) {
            ex.printStackTrace()
            Result.Error("Twitter Feed not received !!")
        }
    }

    private suspend fun <T> whenConnected(apiCall: suspend () -> Result<T>): Result<T> {
        return try {
            if (networkState.isNetworkConnected()) {
                apiCall()
            } else {
                Result.Error(Result.NO_NETWORK)
            }
        } catch (e: Exception) {
            Result.Error()
        }
    }
}
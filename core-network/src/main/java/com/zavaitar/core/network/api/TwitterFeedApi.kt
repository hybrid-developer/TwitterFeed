package com.zavaitar.core.network.api

import com.zavaitar.core.network.model.TwitterFeedResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TwitterFeedApi {

    @GET("/1.1/lists/statuses.json")
    suspend fun getTwitterFeed(
        @Query("list_id") listId: Long,
        @Query("tweet_mode") tweetMode: String,
        @Query("include_entities") entities: Int,
        @Query("count") count: Int
    ): Single<List<TwitterFeedResponse>>
}
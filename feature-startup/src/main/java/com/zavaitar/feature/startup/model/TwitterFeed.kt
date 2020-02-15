package com.zavaitar.feature.startup.model

import com.zavaitar.core.network.model.TwitterFeedResponse

data class TwitterFeed(
    private val createdDate: String,
    private val content: String
)

fun TwitterFeedResponse.convertToFeed() : TwitterFeed {
    return TwitterFeed(createdAt, fullText)
}

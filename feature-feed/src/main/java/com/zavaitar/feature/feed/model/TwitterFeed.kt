package com.zavaitar.feature.feed.model

import com.zavaitar.core.network.model.TwitterFeedResponse

data class TwitterFeed(
    val createdDate: String,
    val content: String
)

fun TwitterFeedResponse.convertToFeed() : TwitterFeed {
    return TwitterFeed(createdAt, fullText)
}

package com.zavaitar.feature.feed.adapter

import com.zavaitar.feature.feed.model.TwitterFeed

interface OnItemSelectedListener {
    fun onItemSelect(item: TwitterFeed?)
}
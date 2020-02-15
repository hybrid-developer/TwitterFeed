package com.zavaitar.feature.feed.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.zavaitar.feature.feed.R
import dagger.android.AndroidInjection

internal class FeedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("FeedActivity", "This is FeedActivity")
        setContentView(R.layout.feed_activity)
        AndroidInjection.inject(this)
    }
}

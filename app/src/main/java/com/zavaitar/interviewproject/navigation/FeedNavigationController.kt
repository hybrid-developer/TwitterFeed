package com.zavaitar.interviewproject.navigation

import android.view.View
import androidx.navigation.Navigation
import com.zavaitar.feature.feed.FeedNavigator
import com.zavaitar.interviewproject.R
import javax.inject.Inject

internal class FeedNavigationController @Inject constructor() : BaseNavigationController(), FeedNavigator {

    override fun navigateToTwitterFeeds(view: View) {
        Navigation.findNavController(view).navigate(R.id.FeedActivity)
    }
}
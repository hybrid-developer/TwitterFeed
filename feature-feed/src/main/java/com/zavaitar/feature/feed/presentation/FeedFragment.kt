package com.zavaitar.feature.feed.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zavaitar.core.viewmodel.ViewModelFactory
import com.zavaitar.feature.feed.FeedNavigator
import com.zavaitar.feature.feed.R
import com.zavaitar.feature.feed.adapter.OnItemSelectedListener
import com.zavaitar.feature.feed.adapter.TwitterFeedRecyclerViewAdapter
import com.zavaitar.feature.feed.model.TwitterFeed
import com.zavaitar.feature.feed.viewmodel.FeedViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.feed_list_fragment.*
import kotlinx.android.synthetic.main.feed_list_fragment.view.*
import javax.inject.Inject

class FeedFragment : Fragment(), OnItemSelectedListener {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var feedNavigator: FeedNavigator

    private val viewModel: FeedViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(FeedViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        feedToolbar.setTitle(R.string.app_name)
        setupRetryButton()
        subscribeObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadTwitterFeed()
    }

    override fun onItemSelect(item: TwitterFeed?) {
        Toast.makeText(context, item!!.content, Toast.LENGTH_SHORT).show()
    }

    private fun subscribeObservers() {
        viewModel.twitterFeedEvent.observe(viewLifecycleOwner, renderTwitterFeedItems)
        viewModel.errorScreenEvent.observe(viewLifecycleOwner, renderErrorScreenEvent)
        viewModel.dataLoadingStopEvent.observe(viewLifecycleOwner, renderDataLoadingStopEvent)
        viewModel.errorMessageDisplayStatusEvent.observe(viewLifecycleOwner,
            renderErrorMessageDisplayEvent)
    }

    private val renderErrorScreenEvent = Observer<Int> { errorMessage ->
        errorMessageTextView.text = getString(errorMessage)
    }

    private val renderDataLoadingStopEvent = Observer<Void> {
        feedLoadingProgressBar.visibility = View.GONE
    }

    private val renderTwitterFeedItems = Observer<List<TwitterFeed>> {
        twitterFeedRecyclerView.adapter = TwitterFeedRecyclerViewAdapter(it, this)
    }

    private val renderErrorMessageDisplayEvent = Observer<Boolean> { visibilityStatus ->
        errorMessageLayout.visibility = if (visibilityStatus) View.VISIBLE else View.GONE
    }

    private fun setupRetryButton() {
        retryNetworkCallButton.setOnClickListener {
            viewModel.loadTwitterFeed()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.feed_list_fragment, container, false)
        if (rootView.twitterFeedRecyclerView is RecyclerView) {
            with(rootView.twitterFeedRecyclerView) {
                layoutManager = LinearLayoutManager(context)
            }
        }
        return rootView
    }
}

package com.zavaitar.feature.startup.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.zavaitar.core.viewmodel.ViewModelFactory
import com.zavaitar.feature.startup.R
import com.zavaitar.feature.startup.navigation.StartupNavigator
import com.zavaitar.feature.startup.viewmodel.StartupViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


internal class StartupFragment : Fragment() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var startupNavigation: StartupNavigator

    private val viewModel: StartupViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(StartupViewModel::class.java)
    }

    override fun onAttach(context: Context) = super.onAttach(context).also {
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.d("Startup Fragment", "This is Startup Fragment")
        return inflater.inflate(R.layout.startup_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.loadTwitterFeed()
       // startupNavigation.navigateToTwitterFeeds(requireView())
    }
}

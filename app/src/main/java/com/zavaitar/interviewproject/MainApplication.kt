package com.zavaitar.interviewproject

import androidx.lifecycle.LifecycleCallbacks
import com.zavaitar.interviewproject.di.ApplicationComponent
import dagger.android.DaggerApplication

class MainApplication : DaggerApplication() {

    override fun onCreate() = super.onCreate().also {
        registerActivityLifecycleCallbacks(LifecycleCallbacks.activityListener())
    }

    override fun applicationInjector(): ApplicationComponent = Config.applicationInjector(this)
}
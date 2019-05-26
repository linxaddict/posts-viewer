package com.machineinsight_it.postviewer

import android.app.Application
import androidx.fragment.app.Fragment
import com.machineinsight_it.postviewer.di.AppComponent
import com.machineinsight_it.postviewer.di.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class PostViewerApplication : Application(), HasSupportFragmentInjector {
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
            .application(this)
            .build()
        component.inject(this)
    }

    override fun supportFragmentInjector() = fragmentInjector
}
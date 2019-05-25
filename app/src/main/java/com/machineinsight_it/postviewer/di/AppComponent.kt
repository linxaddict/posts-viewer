package com.machineinsight_it.postviewer.di

import android.app.Application
import com.machineinsight_it.postviewer.PostViewerApplication
import com.machineinsight_it.postviewer.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, ActivityBuilder::class, FragmentBuilder::class, AppModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: PostViewerApplication)
}
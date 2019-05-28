package com.machineinsight_it.postviewer.di

import android.app.Application
import com.machineinsight_it.postviewer.PostViewerApplication
import com.machineinsight_it.postviewer.data.api.di.ApiModule
import com.machineinsight_it.postviewer.data.db.di.DbModule
import com.machineinsight_it.postviewer.data.repository.di.RepositoryModule
import com.machineinsight_it.postviewer.ui.di.UiModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class, AppModule::class, ApiModule::class, UiModule::class, RepositoryModule::class,
        DbModule::class
    ]
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
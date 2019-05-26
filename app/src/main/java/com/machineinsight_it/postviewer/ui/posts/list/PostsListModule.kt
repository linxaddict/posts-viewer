package com.machineinsight_it.postviewer.ui.posts.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.machineinsight_it.postviewer.ui.di.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(
    includes = [
        PostsListModule.Providers::class
    ]
)
abstract class PostsListModule {
    @ContributesAndroidInjector(
        modules = [
            Injectors::class
        ]
    )
    abstract fun bind(): PostsListFragment

    @Module
    class Providers {
        @Provides
        @IntoMap
        @ViewModelKey(PostsListViewModel::class)
        fun providePostsListViewModel(): ViewModel = PostsListViewModel()
    }

    @Module
    class Injectors {
        @Provides
        fun providePostsListViewModel(
            factory: ViewModelProvider.Factory,
            target: PostsListFragment
        ): PostsListViewModel =
            ViewModelProviders.of(target, factory).get(PostsListViewModel::class.java)
    }
}
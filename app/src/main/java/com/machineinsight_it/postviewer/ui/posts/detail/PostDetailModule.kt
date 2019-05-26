package com.machineinsight_it.postviewer.ui.posts.detail

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
        PostDetailModule.Providers::class]
)
abstract class PostDetailModule {
    @ContributesAndroidInjector(
        modules = [
            Injectors::class
        ]
    )
    abstract fun bind(): PostDetailFragment

    @Module
    class Providers {
        @Provides
        @IntoMap
        @ViewModelKey(PostDetailViewModel::class)
        fun providePostDetailViewModel(): ViewModel = PostDetailViewModel()
    }

    @Module
    class Injectors {
        @Provides
        fun providePostsListViewModel(
            factory: ViewModelProvider.Factory,
            target: PostDetailFragment
        ): PostDetailViewModel =
            ViewModelProviders.of(target, factory).get(PostDetailViewModel::class.java)
    }
}
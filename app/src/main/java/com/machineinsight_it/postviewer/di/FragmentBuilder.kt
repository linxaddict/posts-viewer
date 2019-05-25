package com.machineinsight_it.postviewer.di

import com.machineinsight_it.postviewer.posts.detail.PostDetailFragment
import com.machineinsight_it.postviewer.ui.posts.detail.PostDetailModule
import com.machineinsight_it.postviewer.ui.posts.list.PostsListFragment
import com.machineinsight_it.postviewer.ui.posts.list.PostsListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {
    @ContributesAndroidInjector(modules = [PostsListModule::class])
    abstract fun bindPostsListFragment(): PostsListFragment

    @ContributesAndroidInjector(modules = [PostDetailModule::class])
    abstract fun bindPostDetailFragment(): PostDetailFragment
}
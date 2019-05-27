package com.machineinsight_it.postviewer.data.repository.di

import com.machineinsight_it.postviewer.data.api.PostsApi
import com.machineinsight_it.postviewer.data.repository.CommentsRepository
import com.machineinsight_it.postviewer.data.repository.PostsRepository
import com.machineinsight_it.postviewer.data.repository.UsersRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideCommentsRepository(api: PostsApi): CommentsRepository = CommentsRepository(api)

    @Provides
    @Singleton
    fun providePostsRepository(api: PostsApi): PostsRepository = PostsRepository(api)

    @Provides
    @Singleton
    fun provideUsersRepository(api: PostsApi): UsersRepository = UsersRepository(api)
}
package com.machineinsight_it.postviewer.data.repository.di

import com.machineinsight_it.postviewer.data.api.PostsApi
import com.machineinsight_it.postviewer.data.db.dao.CommentDao
import com.machineinsight_it.postviewer.data.db.dao.PostDao
import com.machineinsight_it.postviewer.data.db.dao.UserDao
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
    fun provideCommentsRepository(api: PostsApi, dao: CommentDao): CommentsRepository = CommentsRepository(api, dao)

    @Provides
    @Singleton
    fun providePostsRepository(api: PostsApi, dao: PostDao): PostsRepository = PostsRepository(api, dao)

    @Provides
    @Singleton
    fun provideUsersRepository(api: PostsApi, dao: UserDao): UsersRepository = UsersRepository(api, dao)
}
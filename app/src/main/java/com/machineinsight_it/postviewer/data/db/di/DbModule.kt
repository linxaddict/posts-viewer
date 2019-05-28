package com.machineinsight_it.postviewer.data.db.di

import android.app.Application
import androidx.room.Room
import com.machineinsight_it.postviewer.data.db.AppDatabase
import com.machineinsight_it.postviewer.data.db.dao.CommentDao
import com.machineinsight_it.postviewer.data.db.dao.PostDao
import com.machineinsight_it.postviewer.data.db.dao.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {
    @Provides
    @Singleton
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java, "posts_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCommentDao(db: AppDatabase): CommentDao = db.commentDao()

    @Provides
    @Singleton
    fun providePostDao(db: AppDatabase): PostDao = db.postDao()

    @Provides
    @Singleton
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()
}
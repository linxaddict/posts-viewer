package com.machineinsight_it.postviewer.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.machineinsight_it.postviewer.data.db.dao.CommentDao
import com.machineinsight_it.postviewer.data.db.dao.PostDao
import com.machineinsight_it.postviewer.data.db.dao.UserDao
import com.machineinsight_it.postviewer.data.db.model.CommentEntity
import com.machineinsight_it.postviewer.data.db.model.PostEntity
import com.machineinsight_it.postviewer.data.db.model.UserEntity

@Database(entities = [UserEntity::class, CommentEntity::class, PostEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun commentDao(): CommentDao

    abstract fun postDao(): PostDao

    abstract fun userDao(): UserDao
}
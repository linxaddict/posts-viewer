package com.machineinsight_it.postviewer.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.machineinsight_it.postviewer.data.db.model.PostEntity

@Dao
interface PostDao {
    @Query("SELECT * FROM posts")
    fun getPosts(): List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(vararg posts: PostEntity)

    @Query("DELETE FROM posts")
    fun clear()
}
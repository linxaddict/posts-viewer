package com.machineinsight_it.postviewer.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.machineinsight_it.postviewer.data.db.model.CommentEntity

@Dao
interface CommentDao {
    @Query("SELECT * FROM comments")
    fun getComments(): List<CommentEntity>

    @Query("SELECT * FROM comments WHERE postId = :postId")
    fun getComments(postId: Int): List<CommentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComments(vararg comments: CommentEntity)

    @Query("DELETE FROM comments")
    fun clear()
}
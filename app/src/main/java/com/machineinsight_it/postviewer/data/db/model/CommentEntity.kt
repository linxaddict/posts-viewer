package com.machineinsight_it.postviewer.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
data class CommentEntity(
    @PrimaryKey
    var id: Int,

    var postId: Int,
    var name: String?,
    var email: String,
    var body: String
)
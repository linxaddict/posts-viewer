package com.machineinsight_it.postviewer.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CommentEntity(
    @PrimaryKey
    var id: Int,

    var postId: Int,
    var name: String?,
    var email: String,
    var body: String
)
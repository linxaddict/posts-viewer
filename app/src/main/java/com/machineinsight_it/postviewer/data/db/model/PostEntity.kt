package com.machineinsight_it.postviewer.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey
    var id: Int,

    var userId: Int,
    var title: String,
    var body: String
)
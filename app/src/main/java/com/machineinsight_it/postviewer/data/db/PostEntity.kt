package com.machineinsight_it.postviewer.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostEntity(
    @PrimaryKey
    var id: Int,

    var userId: Int,
    var title: String,
    var body: String
)
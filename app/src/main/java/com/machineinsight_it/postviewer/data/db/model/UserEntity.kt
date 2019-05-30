package com.machineinsight_it.postviewer.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    var id: Int,

    var name: String?,
    var username: String,
    var email: String,

    var phone: String?,
    var website: String?
)
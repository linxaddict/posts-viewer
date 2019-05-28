package com.machineinsight_it.postviewer.data.db.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    var id: Int,

    var name: String?,
    var username: String,
    var email: String,

    @Embedded
    var address: AddressEntity?,

    var phone: String?,
    var website: String?,

    @Embedded
    var company: CompanyEntity?
)
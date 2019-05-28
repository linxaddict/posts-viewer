package com.machineinsight_it.postviewer.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.PrimaryKey

data class AddressEntity(
    @ColumnInfo(name = "addressId")
    var id: Int?,

    var street: String,
    var suite: String?,
    var city: String,
    var zipcode: String,

    @Embedded
    var geo: GeoEntity?
)
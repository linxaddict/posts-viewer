package com.machineinsight_it.postviewer.data.db

import androidx.room.Embedded
import androidx.room.PrimaryKey

data class AddressEntity(
    @PrimaryKey
    var id: Int,

    var street: String,
    var suite: String?,
    var city: String,
    var zipcode: String,

    @Embedded
    var geo: GeoEntity?
)
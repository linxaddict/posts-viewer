package com.machineinsight_it.postviewer.data.db.mapper

import com.machineinsight_it.postviewer.data.db.model.AddressEntity
import com.machineinsight_it.postviewer.domain.Address

fun AddressEntity.toAddress(): Address {
    return Address(
        street = street,
        suite = suite,
        city = city,
        zipcode = zipcode,
        geo = geo?.toGeo()
    )
}
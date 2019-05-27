package com.machineinsight_it.postviewer.data.api.mapper

import com.machineinsight_it.postviewer.data.api.model.AddressDto
import com.machineinsight_it.postviewer.domain.Address

fun AddressDto.toAddress(): Address? {
    if (street == null) {
        return null
    }

    if (city == null) {
        return null
    }

    if (zipcode == null) {
        return null
    }

    return Address(
        street = street,
        suite = suite,
        city = city,
        zipcode = zipcode,
        geo = geo?.toGeo()
    )
}
package com.machineinsight_it.postviewer.data.api.mapper

import com.machineinsight_it.postviewer.data.api.model.AddressDto
import com.machineinsight_it.postviewer.domain.Address

fun AddressDto.canBeCastToAddress(): Boolean {
    if (street == null) {
        return false
    }

    if (city == null) {
        return false
    }

    if (zipcode == null) {
        return false
    }

    return true
}

fun AddressDto.toAddress(): Address {
    checkNotNull(street)
    checkNotNull(city)
    checkNotNull(zipcode)

    return Address(
        street = street,
        suite = suite,
        city = city,
        zipcode = zipcode,
        geo = geo?.toGeo()
    )
}
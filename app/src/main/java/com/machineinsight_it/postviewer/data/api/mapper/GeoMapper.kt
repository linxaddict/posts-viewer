package com.machineinsight_it.postviewer.data.api.mapper

import com.machineinsight_it.postviewer.data.api.model.GeoDto
import com.machineinsight_it.postviewer.data.db.model.GeoEntity
import com.machineinsight_it.postviewer.domain.Geo

fun GeoDto.canBeCastToGeo(): Boolean {
    if (lat == null) {
        return false
    }

    if (lng == null) {
        return false
    }

    return true
}

fun GeoDto.toGeo(): Geo {
    checkNotNull(lat)
    checkNotNull(lng)

    return Geo(
        lat = lat,
        lng = lng
    )
}

fun GeoDto.toEntity(): GeoEntity {
    checkNotNull(lat)
    checkNotNull(lng)

    return GeoEntity(
        lat = lat,
        lng = lng
    )
}
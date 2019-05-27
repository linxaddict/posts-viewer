package com.machineinsight_it.postviewer.data.api.mapper

import com.machineinsight_it.postviewer.data.api.model.GeoDto
import com.machineinsight_it.postviewer.domain.Geo

fun GeoDto.toGeo(): Geo? {
    if (lat == null) {
        return null
    }

    if (lng == null) {
        return null
    }

    return Geo(
        lat = lat,
        lng = lng
    )
}
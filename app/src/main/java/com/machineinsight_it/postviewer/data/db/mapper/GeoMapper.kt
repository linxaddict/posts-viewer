package com.machineinsight_it.postviewer.data.db.mapper

import com.machineinsight_it.postviewer.data.db.model.GeoEntity
import com.machineinsight_it.postviewer.domain.Geo

fun GeoEntity.toGeo(): Geo = Geo(
    lat = lat,
    lng = lng
)
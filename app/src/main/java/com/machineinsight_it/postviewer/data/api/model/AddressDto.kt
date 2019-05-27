package com.machineinsight_it.postviewer.data.api.model

data class AddressDto(
    val street: String?,
    val suite: String?,
    val city: String?,
    val zipcode: String?,
    val geo: GeoDto?
)
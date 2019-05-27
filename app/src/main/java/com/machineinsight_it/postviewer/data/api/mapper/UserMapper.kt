package com.machineinsight_it.postviewer.data.api.mapper

import com.machineinsight_it.postviewer.data.api.model.UserDto
import com.machineinsight_it.postviewer.domain.User

fun UserDto.toUser(): User? {
    if (id == null) {
        return null
    }

    if (username == null) {
        return null
    }

    if (email == null) {
        return null
    }

    return User(
        id = id,
        name = name,
        username = username,
        email = email,
        address = address?.toAddress(),
        phone = phone,
        website = website,
        company = company?.toCompany()
    )
}
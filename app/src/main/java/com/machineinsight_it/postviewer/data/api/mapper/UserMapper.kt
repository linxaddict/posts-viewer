package com.machineinsight_it.postviewer.data.api.mapper

import com.machineinsight_it.postviewer.data.api.model.UserDto
import com.machineinsight_it.postviewer.data.db.model.UserEntity

fun UserDto.canBeCastToUser(): Boolean {
    if (id == null) {
        return false
    }

    if (username == null) {
        return false
    }

    if (email == null) {
        return false
    }

    return true
}

fun UserDto.toEntity(): UserEntity {
    checkNotNull(id)
    checkNotNull(username)
    checkNotNull(email)

    return UserEntity(
        id = id,
        name = name,
        username = username,
        email = email,
        phone = phone,
        website = website
    )
}
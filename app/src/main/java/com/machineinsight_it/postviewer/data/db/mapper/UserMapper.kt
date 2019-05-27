package com.machineinsight_it.postviewer.data.db.mapper

import com.machineinsight_it.postviewer.data.db.UserEntity
import com.machineinsight_it.postviewer.domain.User

fun UserEntity.toUser(): User = User(
    id = id,
    name = name,
    username = username,
    email = email,
    address = address?.toAddress(),
    phone = phone,
    website = website,
    company = company?.toCompany()
)
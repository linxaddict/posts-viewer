package com.machineinsight_it.postviewer.data.api.mapper

import com.machineinsight_it.postviewer.data.api.model.PostDto
import com.machineinsight_it.postviewer.data.db.model.PostEntity
import com.machineinsight_it.postviewer.domain.Post

fun PostDto.canBeCastToPost(): Boolean {
    if (userId == null) {
        return false
    }

    if (id == null) {
        return false
    }

    if (title == null) {
        return false
    }

    if (body == null) {
        return false
    }

    return true
}

fun PostDto.toPost(): Post {
    checkNotNull(userId)
    checkNotNull(id)
    checkNotNull(title)
    checkNotNull(body)

    return Post(
        userId = userId,
        id = id,
        title = title,
        body = body
    )
}

fun PostDto.toEntity(): PostEntity {
    checkNotNull(userId)
    checkNotNull(id)
    checkNotNull(title)
    checkNotNull(body)

    return PostEntity(
        userId = userId,
        id = id,
        title = title,
        body = body
    )
}
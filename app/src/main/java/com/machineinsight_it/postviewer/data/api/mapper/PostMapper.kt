package com.machineinsight_it.postviewer.data.api.mapper

import com.machineinsight_it.postviewer.data.api.model.PostDto
import com.machineinsight_it.postviewer.domain.Post

fun PostDto.toPost(): Post? {
    if (userId == null) {
        return null
    }

    if (id == null) {
        return id
    }

    if (title == null) {
        return title
    }

    if (body == null) {
        return null
    }

    return Post(
        userId = userId,
        id = id,
        title = title,
        body = body
    )
}
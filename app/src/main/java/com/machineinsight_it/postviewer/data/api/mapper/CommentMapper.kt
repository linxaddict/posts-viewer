package com.machineinsight_it.postviewer.data.api.mapper

import com.machineinsight_it.postviewer.data.api.model.CommentDto
import com.machineinsight_it.postviewer.domain.Comment

fun CommentDto.toComment(): Comment? {
    if (postId == null) {
        return null
    }

    if (id == null) {
        return null
    }

    if (email == null) {
        return null
    }

    if (body == null) {
        return null
    }

    return Comment(
        postId = postId,
        id = id,
        name = name,
        email = email,
        body = body
    )
}
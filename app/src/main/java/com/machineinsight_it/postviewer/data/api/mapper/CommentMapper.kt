package com.machineinsight_it.postviewer.data.api.mapper

import com.machineinsight_it.postviewer.data.api.model.CommentDto
import com.machineinsight_it.postviewer.data.db.model.CommentEntity

fun CommentDto.canBeCastToComment(): Boolean {
    if (postId == null) {
        return false
    }

    if (id == null) {
        return false
    }

    if (email == null) {
        return false
    }

    if (body == null) {
        return false
    }

    return true
}

fun CommentDto.toEntity(): CommentEntity {
    checkNotNull(postId)
    checkNotNull(id)
    checkNotNull(email)
    checkNotNull(body)

    return CommentEntity(
        postId = postId,
        id = id,
        name = name,
        email = email,
        body = body
    )
}
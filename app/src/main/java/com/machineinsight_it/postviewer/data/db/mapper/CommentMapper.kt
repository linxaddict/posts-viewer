package com.machineinsight_it.postviewer.data.db.mapper

import com.machineinsight_it.postviewer.data.db.model.CommentEntity
import com.machineinsight_it.postviewer.domain.Comment

fun CommentEntity.toComment(): Comment = Comment(
    id = id,
    postId = postId,
    name = name,
    email = email,
    body = body
)
package com.machineinsight_it.postviewer.data.db.mapper

import com.machineinsight_it.postviewer.data.db.PostEntity
import com.machineinsight_it.postviewer.domain.Post

fun PostEntity.toPost(): Post = Post(
    id = id,
    userId = userId,
    title = title,
    body = body
)
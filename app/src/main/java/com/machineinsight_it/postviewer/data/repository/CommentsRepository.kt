package com.machineinsight_it.postviewer.data.repository

import com.machineinsight_it.postviewer.data.api.PostsApi
import com.machineinsight_it.postviewer.data.api.mapper.canBeCastToComment
import com.machineinsight_it.postviewer.data.api.mapper.toComment
import com.machineinsight_it.postviewer.domain.Comment
import io.reactivex.Flowable

class CommentsRepository(private val api: PostsApi) {
    fun getComments(): Flowable<Comment> =
        api.getAllComments()
            .toFlowable()
            .flatMapIterable { it }
            .filter { it.canBeCastToComment() }
            .map { it.toComment() }
}
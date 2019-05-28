package com.machineinsight_it.postviewer.data.repository

import com.machineinsight_it.postviewer.data.api.PostsApi
import com.machineinsight_it.postviewer.data.api.mapper.canBeCastToComment
import com.machineinsight_it.postviewer.data.api.mapper.toEntity
import com.machineinsight_it.postviewer.data.db.dao.CommentDao
import com.machineinsight_it.postviewer.data.db.mapper.toComment
import com.machineinsight_it.postviewer.domain.Comment
import io.reactivex.Flowable

class CommentsRepository(private val api: PostsApi, private val dao: CommentDao) {
    fun getComments(): Flowable<Comment> =
        api.fetchComments()
            .toFlowable()
            .flatMapIterable { it }
            .filter { it.canBeCastToComment() }
            .map { it.toEntity() }
            .doOnNext { dao.insertComments(it) }
            .map { it.toComment() }
            .onExceptionResumeNext { dao.getComments().map { it.toComment() } }
}
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
            .toList()
            .toFlowable()
            .doOnNext {
                dao.clear()
                dao.insertComments(*it.toTypedArray())
            }
            .flatMapIterable { it }
            .map { it.toComment() }
            .onErrorResumeNext { _: Throwable ->
                Flowable.fromIterable(dao.getComments()).map { it.toComment() }
            }
}
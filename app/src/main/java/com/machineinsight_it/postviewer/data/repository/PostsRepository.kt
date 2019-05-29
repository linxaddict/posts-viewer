package com.machineinsight_it.postviewer.data.repository

import com.machineinsight_it.postviewer.data.api.PostsApi
import com.machineinsight_it.postviewer.data.api.mapper.canBeCastToPost
import com.machineinsight_it.postviewer.data.api.mapper.toEntity
import com.machineinsight_it.postviewer.data.db.dao.PostDao
import com.machineinsight_it.postviewer.data.db.mapper.toPost
import com.machineinsight_it.postviewer.domain.Post
import io.reactivex.Flowable
import io.reactivex.Observable

class PostsRepository(private val api: PostsApi, private val dao: PostDao) {
    fun getPosts(): Flowable<Post> =
        api.fetchPosts()
            .toFlowable()
            .flatMapIterable { it }
            .filter { it.canBeCastToPost() }
            .map { it.toEntity() }
            .toList()
            .toFlowable()
            .doOnNext {
                dao.clear()
                dao.insertPosts(*it.toTypedArray())
            }
            .flatMapIterable { it }
            .map { it.toPost() }
            .onErrorResumeNext { _: Throwable ->
                Flowable.fromIterable(dao.getPosts()).map { it.toPost() }
            }
}
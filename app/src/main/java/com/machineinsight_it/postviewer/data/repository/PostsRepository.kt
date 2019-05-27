package com.machineinsight_it.postviewer.data.repository

import com.machineinsight_it.postviewer.data.api.PostsApi
import com.machineinsight_it.postviewer.data.api.mapper.canBeCastToPost
import com.machineinsight_it.postviewer.data.api.mapper.toPost
import com.machineinsight_it.postviewer.domain.Post
import io.reactivex.Flowable

class PostsRepository(private val api: PostsApi) {
    fun getPosts(): Flowable<Post> =
        api.fetchPosts()
            .toFlowable()
            .flatMapIterable { it }
            .filter { it.canBeCastToPost() }
            .map { it.toPost() }
}
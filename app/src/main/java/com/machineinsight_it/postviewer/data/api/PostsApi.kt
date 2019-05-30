package com.machineinsight_it.postviewer.data.api

import com.machineinsight_it.postviewer.data.api.model.CommentDto
import com.machineinsight_it.postviewer.data.api.model.PostDto
import com.machineinsight_it.postviewer.data.api.model.UserDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsApi {
    @GET("/posts")
    fun fetchPosts(): Single<List<PostDto>>

    @GET("/comments")
    fun fetchComments(@Query("postId") postId: Int): Single<List<CommentDto>>

    @GET("/users")
    fun fetchUser(@Query("id") userId: Int): Single<List<UserDto>>
}
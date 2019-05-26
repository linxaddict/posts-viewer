package com.machineinsight_it.postviewer.api.model

data class CommentDto(
    val postId: Int?,
    val id: Int?,
    val name: String?,
    val email: String?,
    val body: String?
)
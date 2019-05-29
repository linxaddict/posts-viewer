package com.machineinsight_it.postviewer.ui.posts.detail

import androidx.databinding.ObservableField
import com.machineinsight_it.postviewer.domain.Post
import com.machineinsight_it.postviewer.ui.base.BaseViewModel

class PostDetailViewModel : BaseViewModel() {
    val title = ObservableField<String>()
    val body = ObservableField<String>()

    fun setPost(post: Post) {
        title.set(post.title)
        body.set(post.body)
    }
}
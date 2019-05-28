package com.machineinsight_it.postviewer.ui.posts.list

import androidx.databinding.ObservableField
import com.machineinsight_it.postviewer.domain.Post

class PostViewModel(post: Post) {
    val title = ObservableField<String>(post.title)
    val body = ObservableField<String>(post.body)
}
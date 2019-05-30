package com.machineinsight_it.postviewer.ui.posts.detail

import androidx.databinding.ObservableField
import com.machineinsight_it.postviewer.domain.Comment

class CommentViewModel(comment: Comment) {
    val name = ObservableField<String>(comment.name)
    val email = ObservableField<String>(comment.email)
    val body = ObservableField<String>(comment.body)
    val avatar = ObservableField<String>("https://api.adorable.io/avatars/285/${comment.email}")
}
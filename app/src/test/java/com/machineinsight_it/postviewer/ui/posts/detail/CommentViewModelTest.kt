package com.machineinsight_it.postviewer.ui.posts.detail

import com.machineinsight_it.postviewer.domain.Comment
import org.junit.Test

import org.junit.Assert.*

class CommentViewModelTest {
    private val comment = Comment(1, 2, "n1", "e1", "b1")
    private val model = CommentViewModel(comment)

    @Test
    fun constructor() {
        assertEquals(comment.name, model.name.get())
        assertEquals(comment.email, model.email.get())
        assertEquals(comment.body, model.body.get())
        assertEquals("https://api.adorable.io/avatars/285/${comment.email}", model.avatar.get())
    }
}
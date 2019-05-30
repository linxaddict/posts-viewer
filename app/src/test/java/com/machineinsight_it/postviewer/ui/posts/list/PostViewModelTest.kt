package com.machineinsight_it.postviewer.ui.posts.list

import com.machineinsight_it.postviewer.domain.Post
import org.junit.Assert.*
import org.junit.Test

class PostViewModelTest {
    private val post = Post(1, 2, "t1", "b1")
    private val model = PostViewModel(post)

    @Test
    fun constructor() {
        assertEquals(post.title, model.title.get())
        assertEquals(post.body, model.body.get())
        assertEquals(post, model.post)
    }
}
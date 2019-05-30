package com.machineinsight_it.postviewer.data.db.mapper

import com.machineinsight_it.postviewer.data.db.model.PostEntity
import org.junit.Test

import org.junit.Assert.*

class PostEntityMapperTest {
    @Test
    fun toPost() {
        val entity = PostEntity(1, 2, "title", "body")
        val post = entity.toPost()

        assertEquals(entity.id, post.id)
        assertEquals(entity.userId, post.userId)
        assertEquals(entity.title, post.title)
        assertEquals(entity.body, post.body)
    }
}
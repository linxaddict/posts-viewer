package com.machineinsight_it.postviewer.data.db.mapper

import com.machineinsight_it.postviewer.data.db.model.CommentEntity
import org.junit.Test

import org.junit.Assert.*

class CommentEntityMapperTest {
    @Test
    fun toComment() {
        val entity = CommentEntity(1, 2, "name", "email", "body")
        val comment = entity.toComment()

        assertEquals(entity.id, comment.id)
        assertEquals(entity.postId, comment.postId)
        assertEquals(entity.name, comment.name)
        assertEquals(entity.email, comment.email)
        assertEquals(entity.body, comment.body)
    }
}
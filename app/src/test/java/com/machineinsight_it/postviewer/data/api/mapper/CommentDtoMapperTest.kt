package com.machineinsight_it.postviewer.data.api.mapper

import com.machineinsight_it.postviewer.data.api.model.CommentDto
import org.junit.Assert.*
import org.junit.Test
import java.lang.IllegalStateException

class CommentDtoMapperTest {
    @Test
    fun canBeCastToComment() {
        val dto = CommentDto(1, 2, "name", "email", "body")
        assertTrue(dto.canBeCastToComment())
    }

    @Test
    fun canBeCastToComment_nullPostId() {
        val dto = CommentDto(null, 2, "name", "email", "body")
        assertFalse(dto.canBeCastToComment())
    }

    @Test
    fun canBeCastToComment_nullId() {
        val dto = CommentDto(1, null, "name", "email", "body")
        assertFalse(dto.canBeCastToComment())
    }

    @Test
    fun canBeCastToComment_nullName() {
        val dto = CommentDto(1, 2, null, "email", "body")
        assertTrue(dto.canBeCastToComment())
    }

    @Test
    fun canBeCastToComment_nullBody() {
        val dto = CommentDto(1, 2, "name", "email", null)
        assertFalse(dto.canBeCastToComment())
    }

    @Test
    fun toEntity() {
        val dto = CommentDto(1, 2, "name", "email", "body")
        val entity = dto.toEntity()

        assertEquals(dto.postId, entity.postId)
        assertEquals(dto.id, entity.id)
        assertEquals(dto.name, entity.name)
        assertEquals(dto.email, entity.email)
        assertEquals(dto.body, entity.body)
    }

    @Test(expected = IllegalStateException::class)
    fun toEntity_cannotBeCast() {
        val dto = CommentDto(null, 2, "name", "email", "body")
        dto.toEntity()
    }
}
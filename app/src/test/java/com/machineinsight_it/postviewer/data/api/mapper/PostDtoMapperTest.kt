package com.machineinsight_it.postviewer.data.api.mapper

import com.machineinsight_it.postviewer.data.api.model.PostDto
import org.junit.Test

import org.junit.Assert.*
import java.lang.IllegalStateException

class PostDtoMapperTest {
    @Test
    fun canBeCastToPost() {
        val dto = PostDto(1, 2, "title", "body")
        assertTrue(dto.canBeCastToPost())
    }

    @Test
    fun canBeCastToPost_nullUserId() {
        val dto = PostDto(null, 2, "title", "body")
        assertFalse(dto.canBeCastToPost())
    }

    @Test
    fun canBeCastToPost_nullId() {
        val dto = PostDto(1, null, "title", "body")
        assertFalse(dto.canBeCastToPost())
    }

    @Test
    fun canBeCastToPost_nullTitle() {
        val dto = PostDto(1, 2, null, "body")
        assertFalse(dto.canBeCastToPost())
    }

    @Test
    fun canBeCastToPost_nullBody() {
        val dto = PostDto(1, 2, "title", null)
        assertFalse(dto.canBeCastToPost())
    }

    @Test
    fun toEntity() {
        val dto = PostDto(1, 2, "title", "body")
        val entity = dto.toEntity()

        assertEquals(dto.userId, entity.userId)
        assertEquals(dto.id, entity.id)
        assertEquals(dto.title, entity.title)
        assertEquals(dto.body, entity.body)
    }

    @Test(expected = IllegalStateException::class)
    fun toEntity_cannotBeCast() {
        val dto = PostDto(null, 2, "title", "body")
        dto.toEntity()
    }
}
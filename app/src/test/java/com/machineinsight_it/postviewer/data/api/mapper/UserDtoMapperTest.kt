package com.machineinsight_it.postviewer.data.api.mapper

import com.machineinsight_it.postviewer.data.api.model.UserDto
import org.junit.Test

import org.junit.Assert.*
import java.lang.IllegalStateException

class UserDtoMapperTest {
    @Test
    fun canBeCastToUser() {
        val dto = UserDto(1, "name", "username", "email", "phone", "website")
        assertTrue(dto.canBeCastToUser())
    }

    @Test
    fun canBeCastToUser_nullId() {
        val dto = UserDto(null, "name", "username", "email", "phone", "website")
        assertFalse(dto.canBeCastToUser())
    }

    @Test
    fun canBeCastToUser_nullName() {
        val dto = UserDto(1, null, "username", "email", "phone", "website")
        assertTrue(dto.canBeCastToUser())
    }

    @Test
    fun canBeCastToUser_nullUserName() {
        val dto = UserDto(1, "name", null, "email", "phone", "website")
        assertFalse(dto.canBeCastToUser())
    }

    @Test
    fun canBeCastToUser_nullEmail() {
        val dto = UserDto(1, "name", "username", null, "phone", "website")
        assertFalse(dto.canBeCastToUser())
    }

    @Test
    fun canBeCastToUser_nullPhone() {
        val dto = UserDto(1, "name", "username", "email", null, "website")
        assertTrue(dto.canBeCastToUser())
    }

    @Test
    fun canBeCastToUser_nullWebsite() {
        val dto = UserDto(1, "name", "username", "email", "phone", null)
        assertTrue(dto.canBeCastToUser())
    }

    @Test
    fun toEntity() {
        val dto = UserDto(1, "name", "username", "email", "phone", "website")
        val entity = dto.toEntity()

        assertEquals(dto.id, entity.id)
        assertEquals(dto.name, entity.name)
        assertEquals(dto.username, entity.username)
        assertEquals(dto.email, entity.email)
        assertEquals(dto.phone, entity.phone)
        assertEquals(dto.website, entity.website)
    }

    @Test(expected = IllegalStateException::class)
    fun toEntity_cannotBeCast() {
        val dto = UserDto(null, "name", "username", "email", "phone", "website")
        dto.toEntity()
    }
}
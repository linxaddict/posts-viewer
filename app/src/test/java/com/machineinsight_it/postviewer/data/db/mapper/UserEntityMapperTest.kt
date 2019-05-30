package com.machineinsight_it.postviewer.data.db.mapper

import com.machineinsight_it.postviewer.data.db.model.UserEntity
import org.junit.Test

import org.junit.Assert.*

class UserEntityMapperTest {
    @Test
    fun toUser() {
        val entity = UserEntity(1, "name", "username", "email", "phone", "website")
        val user = entity.toUser()

        assertEquals(entity.id, user.id)
        assertEquals(entity.name, user.name)
        assertEquals(entity.username, user.username)
        assertEquals(entity.email, user.email)
        assertEquals(entity.phone, user.phone)
        assertEquals(entity.website, user.website)
    }
}
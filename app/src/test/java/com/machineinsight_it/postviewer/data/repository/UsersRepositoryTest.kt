package com.machineinsight_it.postviewer.data.repository

import com.machineinsight_it.postviewer.data.api.PostsApi
import com.machineinsight_it.postviewer.data.api.mapper.toEntity
import com.machineinsight_it.postviewer.data.api.model.UserDto
import com.machineinsight_it.postviewer.data.db.dao.UserDao
import com.machineinsight_it.postviewer.data.db.mapper.toUser
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.net.UnknownHostException

class UsersRepositoryTest {
    private val api = mock<PostsApi>()
    private val dao = mock<UserDao>()
    private val repository = UsersRepository(api, dao)

    private val u1 = UserDto(1, "n1", "un1", "e1", "p1", "w1")
    private val u2 = UserDto(null, "n3", "un3", "e3", "p3", "w3")

    private val u1Entity = u1.toEntity()
    private val user1 = u1Entity.toUser()

    @Test
    fun getUser() {
        whenever(api.fetchUser(any())).thenReturn(Single.just(mutableListOf(u1)))

        val testObserver = repository.getUser(1).test()
        testObserver.assertNoErrors()
        testObserver.assertComplete()

        val values = testObserver.values()

        assertEquals(1, testObserver.valueCount())
        assertTrue(values.contains(user1))

        verify(dao).clear()
        verify(dao).insertUsers(u1Entity)
    }

    @Test
    fun getUser_filterOutInvalidUsers() {
        whenever(api.fetchUser(any())).thenReturn(Single.just(mutableListOf(u1, u2)))

        val testObserver = repository.getUser(1).test()
        testObserver.assertNoErrors()
        testObserver.assertComplete()

        val values = testObserver.values()

        assertEquals(1, testObserver.valueCount())
        assertTrue(values.contains(user1))

        verify(dao).clear()
        verify(dao).insertUsers(u1Entity)
    }

    @Test
    fun getUser_noResults() {
        whenever(api.fetchUser(any())).thenReturn(Single.just(emptyList()))

        val testObserver = repository.getUser(1).test()
        testObserver.assertError(NoSuchElementException::class.java)
    }

    @Test
    fun getUser_apiError() {
        whenever(api.fetchUser(any())).thenReturn(Single.error(UnknownHostException()))
        whenever(dao.getUser(any())).thenReturn(u1Entity)

        val testObserver = repository.getUser(1).test()
        testObserver.assertNoErrors()
        testObserver.assertComplete()

        val values = testObserver.values()

        assertEquals(1, testObserver.valueCount())
        assertTrue(values.contains(user1))

        verify(dao, times(0)).clear()
        verify(dao, times(0)).insertUsers(u1Entity)
    }

    @Test
    fun getUser_apiError_noUsersInDb() {
        whenever(api.fetchUser(any())).thenReturn(Single.error(UnknownHostException()))
        whenever(dao.getUser(any())).thenReturn(null)

        val testObserver = repository.getUser(1).test()
        testObserver.assertError(NoSuchElementException::class.java)
    }
}
package com.machineinsight_it.postviewer.data.repository

import com.machineinsight_it.postviewer.data.api.PostsApi
import com.machineinsight_it.postviewer.data.api.mapper.toEntity
import com.machineinsight_it.postviewer.data.api.model.PostDto
import com.machineinsight_it.postviewer.data.db.dao.PostDao
import com.machineinsight_it.postviewer.data.db.mapper.toPost
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.Test

import org.junit.Assert.*
import java.net.UnknownHostException

class PostsRepositoryTest {
    private val api = mock<PostsApi>()
    private val dao = mock<PostDao>()
    private val repository = PostsRepository(api, dao)

    private val p1 = PostDto(1, 2, "t1", "b1")
    private val p2 = PostDto(2, 3, "t2", "b2")
    private val p3 = PostDto(null, 4, "t3", "b3")

    private val p1Entity = p1.toEntity()
    private val p2Entity = p2.toEntity()

    private val post1 = p1Entity.toPost()
    private val post2 = p2Entity.toPost()

    @Test
    fun getPosts() {
        whenever(api.fetchPosts()).thenReturn(Single.just(mutableListOf(p1, p2)))

        val testObserver = repository.getPosts().test()
        testObserver.assertNoErrors()
        testObserver.assertComplete()

        val values = testObserver.values()

        assertEquals(2, testObserver.valueCount())
        assertTrue(values.contains(post1))
        assertTrue(values.contains(post2))

        verify(dao).clear()
        verify(dao).insertPosts(p1Entity, p2Entity)
    }

    @Test
    fun getPosts_filterOutInvalidPosts() {
        whenever(api.fetchPosts()).thenReturn(Single.just(mutableListOf(p1, p2, p3)))

        val testObserver = repository.getPosts().test()
        testObserver.assertNoErrors()
        testObserver.assertComplete()

        val values = testObserver.values()

        assertEquals(2, testObserver.valueCount())
        assertTrue(values.contains(post1))
        assertTrue(values.contains(post2))

        verify(dao).clear()
        verify(dao).insertPosts(p1Entity, p2Entity)
    }

    @Test
    fun getPosts_noResults() {
        whenever(api.fetchPosts()).thenReturn(Single.just(emptyList()))

        val testObserver = repository.getPosts().test()
        testObserver.assertNoErrors()
        testObserver.assertComplete()

        assertEquals(0, testObserver.valueCount())

        verify(dao).clear()
        verify(dao).insertPosts()
    }

    @Test
    fun getPosts_apiError() {
        whenever(api.fetchPosts()).thenReturn(Single.error(UnknownHostException()))
        whenever(dao.getPosts()).thenReturn(listOf(p1Entity, p2Entity))

        val testObserver = repository.getPosts().test()
        testObserver.assertNoErrors()
        testObserver.assertComplete()

        val values = testObserver.values()

        assertEquals(2, testObserver.valueCount())
        assertTrue(values.contains(post1))
        assertTrue(values.contains(post2))

        verify(dao, times(0)).clear()
        verify(dao, times(0)).insertPosts(p1Entity, p2Entity)
    }

    @Test
    fun getPosts_apiError_noPostsInDb() {
        whenever(api.fetchPosts()).thenReturn(Single.error(UnknownHostException()))
        whenever(dao.getPosts()).thenReturn(emptyList())

        val testObserver = repository.getPosts().test()
        testObserver.assertNoErrors()
        testObserver.assertComplete()

        assertEquals(0, testObserver.valueCount())

        verify(dao, times(0)).clear()
        verify(dao, times(0)).insertPosts(p1Entity, p2Entity)
    }
}
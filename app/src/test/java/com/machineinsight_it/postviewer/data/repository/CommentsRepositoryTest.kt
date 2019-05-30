package com.machineinsight_it.postviewer.data.repository

import com.machineinsight_it.postviewer.data.api.PostsApi
import com.machineinsight_it.postviewer.data.api.mapper.toEntity
import com.machineinsight_it.postviewer.data.api.model.CommentDto
import com.machineinsight_it.postviewer.data.db.dao.CommentDao
import com.machineinsight_it.postviewer.data.db.mapper.toComment
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.net.UnknownHostException

class CommentsRepositoryTest {
    private val api = mock<PostsApi>()
    private val dao = mock<CommentDao>()
    private val repository = CommentsRepository(api, dao)

    private val c1 = CommentDto(1, 2, "name", "email", "body")
    private val c2 = CommentDto(2, 3, "nam2", "email2", "body2")
    private val c3 = CommentDto(null, 4, "nam3", "email3", "body3")

    private val c1Entity = c1.toEntity()
    private val c2Entity = c2.toEntity()

    private val comment1 = c1Entity.toComment()
    private val comment2 = c2Entity.toComment()

    @Test
    fun getComments() {
        whenever(api.fetchComments(any())).thenReturn(Single.just(mutableListOf(c1, c2)))

        val testObserver = repository.getComments(1).test()
        testObserver.assertNoErrors()
        testObserver.assertComplete()

        val values = testObserver.values()

        assertEquals(2, testObserver.valueCount())
        assertTrue(values.contains(comment1))
        assertTrue(values.contains(comment2))

        verify(dao).clear()
        verify(dao).insertComments(c1Entity, c2Entity)
    }

    @Test
    fun getComments_filterOutInvalidComments() {
        whenever(api.fetchComments(any())).thenReturn(Single.just(mutableListOf(c1, c2, c3)))

        val testObserver = repository.getComments(1).test()
        testObserver.assertNoErrors()
        testObserver.assertComplete()

        val values = testObserver.values()

        assertEquals(2, testObserver.valueCount())
        assertTrue(values.contains(comment1))
        assertTrue(values.contains(comment2))

        verify(dao).clear()
        verify(dao).insertComments(c1Entity, c2Entity)
    }

    @Test
    fun getComments_noResults() {
        whenever(api.fetchComments(any())).thenReturn(Single.just(emptyList()))

        val testObserver = repository.getComments(1).test()
        testObserver.assertNoErrors()
        testObserver.assertComplete()

        assertEquals(0, testObserver.valueCount())

        verify(dao).clear()
        verify(dao).insertComments()
    }

    @Test
    fun getComments_apiError() {
        whenever(api.fetchComments(any())).thenReturn(Single.error(UnknownHostException()))
        whenever(dao.getComments(any())).thenReturn(listOf(c1Entity, c2Entity))

        val testObserver = repository.getComments(1).test()
        testObserver.assertNoErrors()
        testObserver.assertComplete()

        val values = testObserver.values()

        assertEquals(2, testObserver.valueCount())
        assertTrue(values.contains(comment1))
        assertTrue(values.contains(comment2))

        verify(dao, times(0)).clear()
        verify(dao, times(0)).insertComments(c1Entity, c2Entity)
    }

    @Test
    fun getComments_apiError_noCommentsInDb() {
        whenever(api.fetchComments(any())).thenReturn(Single.error(UnknownHostException()))
        whenever(dao.getComments(any())).thenReturn(emptyList())

        val testObserver = repository.getComments(1).test()
        testObserver.assertNoErrors()
        testObserver.assertComplete()

        assertEquals(0, testObserver.valueCount())

        verify(dao, times(0)).clear()
        verify(dao, times(0)).insertComments(c1Entity, c2Entity)
    }
}
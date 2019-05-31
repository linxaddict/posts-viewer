package com.machineinsight_it.postviewer.ui.posts.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.machineinsight_it.postviewer.data.api.PostsApi
import com.machineinsight_it.postviewer.data.api.model.CommentDto
import com.machineinsight_it.postviewer.data.api.model.UserDto
import com.machineinsight_it.postviewer.data.db.dao.CommentDao
import com.machineinsight_it.postviewer.data.db.dao.UserDao
import com.machineinsight_it.postviewer.data.repository.CommentsRepository
import com.machineinsight_it.postviewer.data.repository.UsersRepository
import com.machineinsight_it.postviewer.domain.Post
import com.machineinsight_it.postviewer.ui.RxImmediateSchedulerRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Assert.*
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import java.net.UnknownHostException

class PostDetailViewModelTest {
    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val api = mock<PostsApi>()
    private val commentDao = mock<CommentDao>()
    private val userDao = mock<UserDao>()
    private val commentsRepository = CommentsRepository(api, commentDao)
    private val usersRepository = UsersRepository(api, userDao)
    private val model = PostDetailViewModel(commentsRepository, usersRepository)

    private val post = Post(1, 2, "t1", "b1")

    private val c1 = CommentDto(1, 2, "name", "email", "body")
    private val c2 = CommentDto(2, 3, "nam2", "email2", "body2")

    private val u1 = UserDto(1, "n1", "un1", "e1", "p1", "w1")

    @Test
    fun loadPostAuthor() {
        whenever(api.fetchUser(any())).thenReturn(Single.just(mutableListOf(u1)))

        model.loadPostAuthor(post)

        assertEquals(u1.username, model.userName.get())
        assertEquals(u1.email, model.email.get())
        assertEquals("https://api.adorable.io/avatars/285/${u1.email}", model.avatar.get())
        assertTrue(model.userVisible.get())
    }

    @Test
    fun loadPostAuthor_cannotFetch() {
        whenever(api.fetchUser(any())).thenReturn(Single.error(UnknownHostException()))
        whenever(userDao.getUser(any())).thenReturn(null)

        model.userVisible.set(true)
        model.loadPostAuthor(post)

        assertFalse(model.userVisible.get())
    }

    @Test
    fun loadComments() {
        whenever(api.fetchComments(any())).thenReturn(Single.just(mutableListOf(c1, c2)))

        model.loadComments(post)

        val commentEmails = model.comments.map { it.email.get() }
        assertEquals(2, commentEmails.size)
        assertEquals(2, model.commentsCount.get())
        assertTrue(commentEmails.contains(c1.email))
        assertTrue(commentEmails.contains(c2.email))
        assertTrue(model.commentsVisible.get())
    }

    @Test
    fun loadComments_cannotFetch() {
        whenever(api.fetchComments(any())).thenReturn(Single.error(UnknownHostException()))
        whenever(commentDao.getComments(any())).thenReturn(null)

        model.commentsVisible.set(true)
        model.loadComments(post)

        assertFalse(model.commentsVisible.get())
    }

    @Test
    fun setPost() {
        whenever(api.fetchUser(any())).thenReturn(Single.error(UnknownHostException()))
        whenever(userDao.getUser(any())).thenReturn(null)
        whenever(api.fetchComments(any())).thenReturn(Single.error(UnknownHostException()))
        whenever(commentDao.getComments(any())).thenReturn(null)

        model.setPost(post)

        assertEquals(post.title, model.title.get())
        assertEquals(post.body, model.body.get())
    }
}
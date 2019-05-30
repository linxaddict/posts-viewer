package com.machineinsight_it.postviewer.ui.posts.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.machineinsight_it.postviewer.R
import com.machineinsight_it.postviewer.data.api.PostsApi
import com.machineinsight_it.postviewer.data.api.mapper.toEntity
import com.machineinsight_it.postviewer.data.api.model.PostDto
import com.machineinsight_it.postviewer.data.db.dao.PostDao
import com.machineinsight_it.postviewer.data.db.mapper.toPost
import com.machineinsight_it.postviewer.data.repository.PostsRepository
import com.machineinsight_it.postviewer.ui.RxImmediateSchedulerRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Assert.*
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import java.net.UnknownHostException

class PostsListViewModelTest {
    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val api = mock<PostsApi>()
    private val dao = mock<PostDao>()
    private val repository = PostsRepository(api, dao)
    private val model = PostsListViewModel(repository)

    private val p1 = PostDto(1, 2, "t1", "b1")
    private val p2 = PostDto(2, 3, "t2", "b2")

    @Test
    fun fetchPosts() {
        whenever(api.fetchPosts()).thenReturn(Single.just(mutableListOf(p1, p2)))

        model.fetchPosts()

        val fetchedPosts = model.posts.map { it.post }
        assertTrue(fetchedPosts.contains(p1.toEntity().toPost()))
        assertTrue(fetchedPosts.contains(p2.toEntity().toPost()))
        assertFalse(model.fetchInProgress.get())
    }

    @Test
    fun fetchPosts_cannotFetch() {
        whenever(api.fetchPosts()).thenReturn(Single.error(UnknownHostException()))
        whenever(dao.getPosts()).thenReturn(null)

        val mockObserver = mock<Observer<Int>>()
        model.fetchingErrorEvent.observeForever(mockObserver)
        model.fetchPosts()

        verify(mockObserver).onChanged(R.string.error_cannot_fetch_posts)
    }
}
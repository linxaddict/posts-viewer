package com.machineinsight_it.postviewer.ui.posts.list

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import com.machineinsight_it.postviewer.R
import com.machineinsight_it.postviewer.data.repository.PostsRepository
import com.machineinsight_it.postviewer.ui.base.BaseViewModel
import com.machineinsight_it.postviewer.ui.base.event.ResourceTextMessageEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PostsListViewModel(private val postsRepository: PostsRepository) : BaseViewModel() {
    val posts = ObservableArrayList<PostViewModel>()
    val fetchInProgress = ObservableBoolean()
    val fetchingErrorEvent = ResourceTextMessageEvent()

    private fun handlePostsFetched(results: List<PostViewModel>) {
        posts.clear()
        posts.addAll(results)
    }

    private fun handlePostsFetchingError() {
        fetchingErrorEvent.call(R.string.error_cannot_fetch_posts)
    }

    fun fetchPosts() {
        postsRepository.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { fetchInProgress.set(true) }
            .doFinally { fetchInProgress.set(false) }
            .map { PostViewModel(it) }
            .toList()
            .subscribe(
                { handlePostsFetched(it) },
                { handlePostsFetchingError() }
            )
            .register()
    }
}
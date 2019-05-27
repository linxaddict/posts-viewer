package com.machineinsight_it.postviewer.ui.posts.list

import androidx.lifecycle.ViewModel
import com.machineinsight_it.postviewer.data.api.PostsApi
import com.machineinsight_it.postviewer.data.repository.PostsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PostsListViewModel(private val postsRepository: PostsRepository) : ViewModel() {
    fun fetch() {
        val disposable = postsRepository.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                System.out.println(it)
            }
    }
}
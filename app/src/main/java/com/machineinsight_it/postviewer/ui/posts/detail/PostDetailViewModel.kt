package com.machineinsight_it.postviewer.ui.posts.detail

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.machineinsight_it.postviewer.data.repository.CommentsRepository
import com.machineinsight_it.postviewer.data.repository.UsersRepository
import com.machineinsight_it.postviewer.domain.Post
import com.machineinsight_it.postviewer.domain.User
import com.machineinsight_it.postviewer.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class PostDetailViewModel(private val commentsRepository: CommentsRepository,
                          private val usersRepository: UsersRepository) : BaseViewModel() {
    val title = ObservableField<String>()
    val body = ObservableField<String>()
    val userName = ObservableField<String>()
    val email = ObservableField<String>()
    val avatar = ObservableField<String>()
    val comments = ObservableArrayList<CommentViewModel>()

    val userVisible = ObservableBoolean()
    val commentsVisible = ObservableBoolean()

    private fun handleAuthorLoaded(it: User) {
        userName.set(it.username)
        email.set(it.email)
        avatar.set("https://api.adorable.io/avatars/285/${it.email}")
        userVisible.set(true)
    }

    private fun handleCannotLoadAuthor() {
        userVisible.set(false)
    }

    private fun handleCommentsLoaded(it: MutableList<CommentViewModel>) {
        comments.clear()
        comments.addAll(it)
        commentsVisible.set(true)
    }

    private fun handleCannotLoadComments() {
        commentsVisible.set(false)
    }

    private fun loadPostAuthor(post: Post) {
        usersRepository.getUser(post.userId)
            .delay(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { handleAuthorLoaded(it) },
                { handleCannotLoadAuthor() }
            )
            .register()
    }

    private fun loadComments(post: Post) {
        commentsRepository.getComments(post.id)
            .delay(1000, TimeUnit.MILLISECONDS)
            .map { CommentViewModel(it) }
            .toList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { handleCommentsLoaded(it) },
                { handleCannotLoadComments() }
            )
            .register()
    }

    fun setPost(post: Post) {
        title.set(post.title)
        body.set(post.body)

        loadPostAuthor(post)
        loadComments(post)
    }
}
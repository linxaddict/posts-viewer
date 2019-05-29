package com.machineinsight_it.postviewer.ui.posts.detail

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
    val avatar = ObservableField<String>("https://api.adorable.io/avatars/285/abott@adorable.png")

    val userVisible = ObservableBoolean()

    fun setPost(post: Post) {
        title.set(post.title)
        body.set(post.body)

        usersRepository.getUser(post.userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .delay(500, TimeUnit.MILLISECONDS)
            .subscribe(
                {
                    userName.set(it.username)
                    email.set(it.email)
                    userVisible.set(true)
                },
                {
                    userVisible.set(false)
                }
            )
            .register()
    }
}
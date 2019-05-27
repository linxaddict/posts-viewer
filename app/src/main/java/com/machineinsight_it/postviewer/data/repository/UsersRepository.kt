package com.machineinsight_it.postviewer.data.repository

import com.machineinsight_it.postviewer.data.api.PostsApi
import com.machineinsight_it.postviewer.data.api.mapper.canBeCastToUser
import com.machineinsight_it.postviewer.data.api.mapper.toUser
import com.machineinsight_it.postviewer.domain.User
import io.reactivex.Flowable

class UsersRepository(private val api: PostsApi) {
    fun getUsers(): Flowable<User> =
        api.getAllUsers()
            .toFlowable()
            .flatMapIterable { it }
            .filter { it.canBeCastToUser() }
            .map { it.toUser() }
}
package com.machineinsight_it.postviewer.data.repository

import com.machineinsight_it.postviewer.data.api.PostsApi
import com.machineinsight_it.postviewer.data.api.mapper.canBeCastToUser
import com.machineinsight_it.postviewer.data.api.mapper.toEntity
import com.machineinsight_it.postviewer.data.db.dao.UserDao
import com.machineinsight_it.postviewer.data.db.mapper.toPost
import com.machineinsight_it.postviewer.data.db.mapper.toUser
import com.machineinsight_it.postviewer.domain.User
import io.reactivex.Flowable

class UsersRepository(private val api: PostsApi, private val dao: UserDao) {
    fun getUsers(): Flowable<User> =
        api.fetchUsers()
            .toFlowable()
            .flatMapIterable { it }
            .filter { it.canBeCastToUser() }
            .map { it.toEntity() }
            .toList()
            .toFlowable()
            .doOnNext {
                dao.clear()
                dao.insertUsers(*it.toTypedArray())
            }
            .flatMapIterable { it }
            .map { it.toUser() }
            .onErrorResumeNext { _: Throwable ->
                Flowable.fromIterable(dao.getUsers()).map { it.toUser() }
            }
}
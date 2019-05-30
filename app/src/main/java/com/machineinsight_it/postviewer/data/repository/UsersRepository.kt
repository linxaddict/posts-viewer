package com.machineinsight_it.postviewer.data.repository

import com.machineinsight_it.postviewer.data.api.PostsApi
import com.machineinsight_it.postviewer.data.api.mapper.canBeCastToUser
import com.machineinsight_it.postviewer.data.api.mapper.toEntity
import com.machineinsight_it.postviewer.data.db.dao.UserDao
import com.machineinsight_it.postviewer.data.db.mapper.toUser
import com.machineinsight_it.postviewer.domain.User
import io.reactivex.Single

class UsersRepository(private val api: PostsApi, private val dao: UserDao) {
    fun getUser(id: Int): Single<User> =
        api.fetchUser(id)
            .toFlowable()
            .flatMapIterable { it }
            .filter { it.canBeCastToUser() }
            .map { it.toEntity() }
            .firstOrError()
            .doOnSuccess {
                dao.clear()
                dao.insertUsers(it)
            }
            .map { it.toUser() }
            .onErrorResumeNext { _: Throwable ->
                val user = dao.getUser(id)
                if (user != null) {
                    Single.just(dao.getUser(id)).map { it.toUser() }
                } else {
                    return@onErrorResumeNext Single.error(NoSuchElementException())
                }
            }
}
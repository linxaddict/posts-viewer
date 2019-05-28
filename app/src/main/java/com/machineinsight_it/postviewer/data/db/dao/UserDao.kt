package com.machineinsight_it.postviewer.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.machineinsight_it.postviewer.data.db.model.UserEntity
import com.machineinsight_it.postviewer.domain.User
import io.reactivex.Flowable

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getUsers(): Flowable<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(vararg users: UserEntity)

    @Query("DELETE FROM users")
    fun clear()
}
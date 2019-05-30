package com.machineinsight_it.postviewer.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.machineinsight_it.postviewer.data.db.model.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getUsers(): List<UserEntity>

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUser(id: Int): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(vararg users: UserEntity)

    @Query("DELETE FROM users")
    fun clear()
}
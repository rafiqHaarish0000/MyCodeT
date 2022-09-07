package com.example.mycode.Room_Databse.user_dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mycode.Room_Databse.model.UserData

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUserdata(userDao: UserData)

    @Update
    suspend fun updateUserData(userData: UserData)

    @Delete
    suspend fun deleteUserData(userData: UserData)

    @Query("DELETE FROM user_table ")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData():LiveData<List<UserData>>
}
package com.example.mycode.Room_Databse.Database_repository

import androidx.lifecycle.LiveData
import com.example.mycode.Room_Databse.model.UserData
import com.example.mycode.Room_Databse.user_dao.UserDao

class UserRepository(private val userDao: UserDao) {

    val readAllData:LiveData<List<UserData>> = userDao.readAllData()

    suspend fun addUser(userData: UserData){
       userDao.insertUserdata(userDao = userData)
    }
    suspend fun updateUser(userData: UserData){
        userDao.updateUserData(userData = userData)
    }
    suspend fun deleteUser(userData: UserData){
        userDao.deleteUserData(userData = userData)
    }
    suspend fun deleteAllUsers(){
        userDao.deleteAllUsers()
    }
}
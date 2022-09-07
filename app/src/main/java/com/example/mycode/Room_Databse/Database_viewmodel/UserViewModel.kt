package com.example.mycode.Room_Databse.Database_viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mycode.Room_Databse.Database_repository.UserRepository
import com.example.mycode.Room_Databse.MainDatabase.MainUserdata
import com.example.mycode.Room_Databse.model.UserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application):AndroidViewModel(application) {
    val readAllData:LiveData<List<UserData>>
    private val repository:UserRepository

    init {
        val userDao = MainUserdata.getDatabase(application).getDao()
        repository = UserRepository(userDao = userDao)
        readAllData = repository.readAllData
    }
    fun addUser(userData: UserData){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(userData)
        }
    }
    fun updateUser(userData: UserData){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(userData)
        }
    }
    fun deleteUser(userData: UserData){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteUser(userData)
        }
    }
    fun deleteAllUsers(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllUsers()
        }
    }
}
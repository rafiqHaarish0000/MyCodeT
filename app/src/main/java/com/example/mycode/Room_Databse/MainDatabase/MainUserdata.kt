package com.example.mycode.Room_Databse.MainDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mycode.Room_Databse.model.UserData
import com.example.mycode.Room_Databse.user_dao.UserDao

@Database(entities = [UserData::class], version = 1, exportSchema = false)
abstract class MainUserdata : RoomDatabase() {
    abstract fun getDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: MainUserdata? = null

        fun getDatabase(context: Context): MainUserdata {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, MainUserdata::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
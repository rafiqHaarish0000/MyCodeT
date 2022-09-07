package com.example.mycode.Room_Databse.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class UserData(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var firstName: String,
    var lastName: String,
    var age:Int
):Parcelable

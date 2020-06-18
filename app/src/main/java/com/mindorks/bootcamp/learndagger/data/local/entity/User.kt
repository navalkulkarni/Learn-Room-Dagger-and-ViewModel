package com.mindorks.bootcamp.learndagger.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(

        @PrimaryKey(autoGenerate = true)
        val id:Long = 0,

        @ColumnInfo(name = "name")
        val name:String

)
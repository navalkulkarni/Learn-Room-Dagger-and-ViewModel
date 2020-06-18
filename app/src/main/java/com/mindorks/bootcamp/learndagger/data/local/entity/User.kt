package com.mindorks.bootcamp.learndagger.data.local.entity

import androidx.room.*

@Entity(tableName = "users")
data class User(

        @PrimaryKey(autoGenerate = true)
        var id:Long = 0,

        @ColumnInfo(name = "name")
        var name:String,

        @Embedded
        var address: Address,

        @Ignore
        var selected:Boolean = false
){
        constructor():this(0,"", Address("",""),false)
}
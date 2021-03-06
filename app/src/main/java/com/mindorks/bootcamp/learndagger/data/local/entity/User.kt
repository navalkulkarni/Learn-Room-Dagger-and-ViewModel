package com.mindorks.bootcamp.learndagger.data.local.entity

import androidx.room.*
import java.util.*

@Entity(tableName = "users",
        foreignKeys = [
        ForeignKey(entity = Address::class
        ,parentColumns = ["id"],
                childColumns = ["addressId"],
                onDelete = ForeignKey.CASCADE
        )
        ]

)
data class User(

        @PrimaryKey(autoGenerate = true)
        var id:Long = 0,

        @ColumnInfo(name = "name")
        var name:String,

        @ColumnInfo(name="addressId")
        var addressId: Long,

        @ColumnInfo(name="date_of_birth")
        var dob : Date,

        @Ignore
        var selected:Boolean = false
){
        constructor():this(0,"", 0,Date(),false)
}
package com.mindorks.bootcamp.learndagger.utils

import androidx.room.TypeConverter
import java.util.*

class Converter {

    @TypeConverter
    fun fromTimeStamp(value:Long?)  = value?.let { Date(it) }

    @TypeConverter
    fun fromDate(value: Date?) = value?.time
}
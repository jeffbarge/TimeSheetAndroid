package com.barger.timesheet.data

import android.arch.persistence.room.TypeConverter
import org.joda.time.DateTime

class Converters {

    @TypeConverter
    fun fromDateString(date: String?) : DateTime? {
        return if (date == null) null else DateTime.parse(date)
    }

    @TypeConverter
    fun dateToString(date: DateTime): String {
        return date.toString()
    }

}
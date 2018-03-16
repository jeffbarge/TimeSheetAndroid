package com.barger.timesheet.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters

@Database(entities = arrayOf(Project::class, Task::class, TimeEntry::class), version = 2)
@TypeConverters(Converters::class)
abstract class TimesheetDatabase : RoomDatabase() {
    abstract fun timesheetDao() : TimesheetDao
}
package com.barger.timesheet.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import org.joda.time.DateTime

@Entity(tableName = "timeEntries",
        foreignKeys = arrayOf(ForeignKey(
                entity = Project::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("projectId")
        )))
class TimeEntry {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var projectId: Int = 0
    var startTime: DateTime = DateTime.parse("")
    var endTime: DateTime = DateTime.parse("")
}

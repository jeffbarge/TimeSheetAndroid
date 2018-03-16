package com.barger.timesheet.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "tasks",
        foreignKeys = arrayOf(
                ForeignKey(
                        entity = Project::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("projectId"))
        ))
class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var projectId: Long,
    var name: String,
    var chargeCode: String
)
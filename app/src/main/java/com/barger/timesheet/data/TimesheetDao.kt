package com.barger.timesheet.data

import android.arch.persistence.room.*
import io.reactivex.Flowable

@Dao
interface TimesheetDao {
    @Insert
    fun insertProject(project: Project)

    @Update
    fun updateProject(project: Project)

    @Query("SELECT * FROM projects")
    fun getAllProjects() : Flowable<List<Project>>

    @Delete
    fun deleteProject(project: Project)

    @Insert
    fun insertTimeEntry(timeEntry: TimeEntry)

    @Update
    fun updateTimeEntry(timeEntry: TimeEntry)

    @Query("SELECT * FROM timeEntries WHERE projectId = :projectId")
    fun getTimeEntriesForProject(projectId: Int) : Flowable<List<TimeEntry>>
}
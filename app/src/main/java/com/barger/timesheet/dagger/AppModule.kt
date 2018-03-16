package com.barger.timesheet.dagger

import android.app.Application
import android.arch.persistence.room.Room
import com.barger.timesheet.data.TimesheetDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val application: Application) {

    @Provides
    @Singleton
    fun providesApplication() : Application = application

    @Provides
    @Singleton
    fun providesDatabase() : TimesheetDatabase {
        return Room
                .databaseBuilder(application.applicationContext, TimesheetDatabase::class.java, "timesheet-db")
                .allowMainThreadQueries()
                .build()
    }

}
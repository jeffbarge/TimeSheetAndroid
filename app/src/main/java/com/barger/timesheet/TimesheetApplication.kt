package com.barger.timesheet

import android.app.Application
import android.support.multidex.MultiDexApplication
import com.barger.timesheet.dagger.AppComponent
import com.barger.timesheet.dagger.AppModule
import com.barger.timesheet.dagger.DaggerAppComponent
import net.danlew.android.joda.JodaTimeAndroid

class TimesheetApplication : MultiDexApplication() {

    var appComponent: AppComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        private set

    override fun onCreate() {
        super.onCreate()
        JodaTimeAndroid.init(this)
    }
}
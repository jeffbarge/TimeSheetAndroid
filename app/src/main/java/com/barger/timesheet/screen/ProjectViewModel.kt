package com.barger.timesheet.screen

import com.barger.timesheet.data.Project

class ProjectViewModel(val project: Project) {
    val name: String
        get() = project.name
}
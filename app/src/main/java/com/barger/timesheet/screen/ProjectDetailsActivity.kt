package com.barger.timesheet.screen

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import barger.com.timesheet.R
import barger.com.timesheet.databinding.ActivityProjectDetailsBinding
import com.barger.timesheet.data.Project
import com.barger.timesheet.data.TimesheetDatabase
import javax.inject.Inject

class ProjectDetailsActivity : AppCompatActivity() {

    lateinit var db : TimesheetDatabase
        @Inject set

    private lateinit var project: Project
    private lateinit var binding: ActivityProjectDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_project_details)
        project = intent.getParcelableExtra(EXTRA_PROJECT)
        binding.model = ProjectViewModel(project)
    }

    companion object {
        private const val EXTRA_PROJECT = "extra:project"

        fun createIntent(context: Context, project: Project) : Intent {
            val intent = Intent(context, ProjectDetailsActivity::class.java)
            intent.putExtra(EXTRA_PROJECT, project)
            return intent
        }
    }
}
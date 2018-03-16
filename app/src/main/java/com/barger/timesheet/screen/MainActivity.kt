package com.barger.timesheet.screen

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import barger.com.timesheet.R
import barger.com.timesheet.databinding.ActivityMainBinding
import barger.com.timesheet.databinding.ListItemProjectCardBinding

import com.barger.timesheet.TimesheetApplication
import com.barger.timesheet.data.Project
import com.barger.timesheet.data.TimesheetDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : AppCompatActivity(), AddProjectDialog.ProjectAddedListener {

    lateinit var db : TimesheetDatabase
        @Inject set

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ProjectListAdapter
    private lateinit var dbDisposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.presenter = Presenter()
        (application as TimesheetApplication).appComponent.inject(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        loadProjects()
    }

    override fun onProjectAdded(project: Project) {
        db.timesheetDao().insertProject(project)
        adapter.addProject(project)
    }

    private fun loadProjects() {
        dbDisposable = db.timesheetDao()
                .getAllProjects()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { projects ->
                    adapter = ProjectListAdapter(projects)
                    adapter.deleteClickedListener = { db.timesheetDao().deleteProject(it) }
                    adapter.projectDetailsClickedListener = { startActivity(ProjectDetailsActivity.createIntent(this, it)) }
                    binding.recyclerView.adapter = adapter
                }
    }

    override fun onDestroy() {
        super.onDestroy()
        dbDisposable.dispose()
    }

    class BindingViewHolder(val binding: ListItemProjectCardBinding) : RecyclerView.ViewHolder(binding.root)

    class ProjectListAdapter(private var data: List<Project>) : RecyclerView.Adapter<BindingViewHolder>() {

        var deleteClickedListener: (Project) -> Unit = { }
        var projectDetailsClickedListener: (Project) -> Unit = { }
        var startTimerClickedListener: (Project) -> Unit = { }

        fun addProject(project: Project) {
            data += project
            notifyItemInserted(data.size - 1)
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BindingViewHolder {
            val inflater = LayoutInflater.from(parent?.context)
            return BindingViewHolder(ListItemProjectCardBinding.inflate(inflater, parent, false))
        }

        override fun onBindViewHolder(viewHolder: BindingViewHolder?, position: Int) {
            viewHolder?.binding?.deleteButton?.setOnClickListener {
                deleteClickedListener(data[position])
            }

            viewHolder?.binding?.content?.setOnClickListener {
                projectDetailsClickedListener(data[position])
            }


            viewHolder?.binding?.model = ProjectViewModel(data[position])
            viewHolder?.binding?.executePendingBindings()
        }

        override fun getItemCount(): Int {
            return data.size
        }
    }

    inner class Presenter {
        fun addProjectClicked() {
            AddProjectDialog.newInstance().show(supportFragmentManager, "")
        }
    }
}

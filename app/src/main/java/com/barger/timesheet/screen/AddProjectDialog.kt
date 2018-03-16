package com.barger.timesheet.screen

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import barger.com.timesheet.databinding.DialogAddProjectBinding
import com.barger.timesheet.data.Project

class AddProjectDialog : DialogFragment() {
    companion object {
        fun newInstance() : AddProjectDialog {
            return AddProjectDialog()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        val binding = DialogAddProjectBinding.inflate(LayoutInflater.from(context), null, false)
        val view = binding.root

        return builder.setTitle("Add project")
                .setView(view)
                .setPositiveButton("Save",  { _, _ -> run {
                    val project = Project(0, binding.projectName.editText?.text.toString())
                    getProjectAddedListener().onProjectAdded(project)
                }})
                .create()
    }

    private fun getProjectAddedListener(): ProjectAddedListener {
        val activityListener = activity as ProjectAddedListener?
        val fragmentListener = targetFragment as ProjectAddedListener?
        if (activityListener != null) {
            return activityListener
        } else if (fragmentListener != null) {
            return fragmentListener
        } else {
            throw IllegalArgumentException("Must implement ProjectAddedListener")
        }
    }

    interface ProjectAddedListener {
        fun onProjectAdded(project: Project)
    }
}
package com.pawnation.timetracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pawnation.timetracker.R
import com.pawnation.timetracker.adapters.TaskListAdapter
import com.pawnation.timetracker.databinding.TasksFragmentBinding
import com.pawnation.timetracker.viewmodels.TasksViewModel

class TasksFragment: Fragment() {
    private lateinit var tasksViewModel: TasksViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<TasksFragmentBinding>(inflater, R.layout.tasks_fragment, container, false)
        tasksViewModel = ViewModelProvider(this).get(TasksViewModel::class.java)
        binding.tasksViewModel = tasksViewModel
        binding.lifecycleOwner = this
        binding.backButton.setOnClickListener{
            val navController = findNavController()
            navController.navigate(R.id.action_tasksFragment_to_timerFragment)
        }

        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        val adapter = TaskListAdapter()

        binding.tasksRecyclerView.adapter = adapter
        binding.tasksRecyclerView.layoutManager = linearLayoutManager

        tasksViewModel.allTasks.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })

        return binding.root
    }
}
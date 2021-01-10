package com.pawnation.timetracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.pawnation.timetracker.R
import com.pawnation.timetracker.databinding.TimerFragmentBinding
import com.pawnation.timetracker.viewmodels.TimerViewModel

class TimerFragment: Fragment() {
    private lateinit var timerViewModel: TimerViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<TimerFragmentBinding>(inflater, R.layout.timer_fragment, container, false)
        timerViewModel = ViewModelProvider(this).get(TimerViewModel::class.java)
        binding.timerViewModel = timerViewModel
        binding.lifecycleOwner = this
        binding.listButton.setOnClickListener{
            val navController = findNavController()
            navController.navigate(R.id.action_timerFragment_to_tasksFragment)
        }

        return binding.root
    }
}
package com.example.mytestingproject.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mytestingproject.R
import com.example.mytestingproject.databinding.MainFragmentLayoutBinding
import com.example.mytestingproject.utils.safeNavigate

class MainFragment : Fragment(R.layout.main_fragment_layout) {

    private lateinit var binding: MainFragmentLayoutBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainFragmentLayoutBinding.bind(view)

        binding.goToFirstFragmentBtn.setOnClickListener {
            goTo(R.id.action_mainFragment_to_questionOneFragment)
        }

        binding.goToTwoFragmentBtn.setOnClickListener {
            goTo(R.id.action_mainFragment_to_questionTwoFragment)
        }

        binding.goToThreeFragmentBtn.setOnClickListener {
            goTo(R.id.action_mainFragment_to_questionThreeFragment)
        }

    }

    private fun goTo(action:Int) {
        findNavController().safeNavigate(action)
    }
}
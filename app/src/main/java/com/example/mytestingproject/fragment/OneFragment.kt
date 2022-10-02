package com.example.mytestingproject.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mytestingproject.MainActivity
import com.example.mytestingproject.R
import com.example.mytestingproject.databinding.OneFragmentLayoutBinding
import com.example.mytestingproject.utils.msg

class OneFragment : Fragment(R.layout.one_fragment_layout) {
    private lateinit var binding: OneFragmentLayoutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = OneFragmentLayoutBinding.bind(view)

        binding.btnCreate.setOnClickListener {
            val action = OneFragmentDirections.actionOneFragmentToTwoFragment("Two Fragment")
            findNavController().navigate(action)
        }
        MainActivity.intented?.let {
            val str = it.getStringExtra("ID_ONE")
            if (str != null) {
                val action = OneFragmentDirections.actionOneFragmentToThreeFragment(str)
                findNavController().navigate(action)
            }
        } ?: activity?.msg("intent is null")
    }



}
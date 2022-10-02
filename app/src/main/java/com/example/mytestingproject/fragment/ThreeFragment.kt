package com.example.mytestingproject.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mytestingproject.R
import com.example.mytestingproject.databinding.ThreeFragmentLayoutBinding

class ThreeFragment:Fragment(R.layout.three_fragment_layout) {
    private lateinit var binding: ThreeFragmentLayoutBinding
    private val args:ThreeFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=ThreeFragmentLayoutBinding.bind(view)
        binding.titleTxt.text=args.title
        binding.btnCreate.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
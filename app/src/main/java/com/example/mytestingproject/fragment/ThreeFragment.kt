package com.example.mytestingproject.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mytestingproject.R
import com.example.mytestingproject.databinding.ThreeFragmentLayoutBinding

class ThreeFragment:Fragment(R.layout.three_fragment_layout) {
    private lateinit var binding: ThreeFragmentLayoutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=ThreeFragmentLayoutBinding.bind(view)
    }
}
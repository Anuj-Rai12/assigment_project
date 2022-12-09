package com.example.mytestingproject.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mytestingproject.R
import com.example.mytestingproject.databinding.OneFragmentLayoutBinding

class OneFragment : Fragment(R.layout.one_fragment_layout) {

    private lateinit var binding: OneFragmentLayoutBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= OneFragmentLayoutBinding.bind(view)
    }

}
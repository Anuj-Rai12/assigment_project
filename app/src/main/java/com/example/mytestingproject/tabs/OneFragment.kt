package com.example.mytestingproject.tabs

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.mytestingproject.R
import com.example.mytestingproject.databinding.OneFragmentLayoutBinding


class OneFragment(private val txt: Int, private val color: Int) :
    Fragment(R.layout.one_fragment_layout) {
    private lateinit var binding: OneFragmentLayoutBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = OneFragmentLayoutBinding.bind(view)
        binding.txtLayout.text = txt.toString()
        binding.txtLayout.setBackgroundColor(resources.getColor(color, null))
    }

}
package com.example.mytestingproject.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mytestingproject.R
import com.example.mytestingproject.databinding.TwoFragmentBinding

class TwoFragment : Fragment(R.layout.two_fragment) {
    private lateinit var binding: TwoFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = TwoFragmentBinding.bind(view)

    }
}
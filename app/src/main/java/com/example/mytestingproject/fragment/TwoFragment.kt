package com.example.mytestingproject.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.mytestingproject.R
import com.example.mytestingproject.databinding.TwoFragmentBinding
import com.example.mytestingproject.utils.msg

class TwoFragment : Fragment(R.layout.two_fragment) {
    private lateinit var binding: TwoFragmentBinding
    private val args: TwoFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = TwoFragmentBinding.bind(view)
        binding.titleTxt.text=args.title
        binding.btnCreate.setOnClickListener {
         activity?.msg(args.title)
        }
    }
}
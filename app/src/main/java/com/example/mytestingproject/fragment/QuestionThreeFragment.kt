package com.example.mytestingproject.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mytestingproject.R
import com.example.mytestingproject.databinding.QuestionThreeLayoutBinding
import com.example.mytestingproject.utils.changeStatusBarColor

class QuestionThreeFragment : Fragment(R.layout.question_three_layout) {

    private lateinit var binding: QuestionThreeLayoutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = QuestionThreeLayoutBinding.bind(view)
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        activity?.changeStatusBarColor()
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

}
package com.example.mytestingproject.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mytestingproject.R
import com.example.mytestingproject.databinding.QuestionTwoScreenLayoutBinding

class QuestionTwoFragment : Fragment(R.layout.question_two_screen_layout) {

    private lateinit var binding: QuestionTwoScreenLayoutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = QuestionTwoScreenLayoutBinding.bind(view)

    }
}
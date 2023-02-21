package com.example.mytestingproject.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mytestingproject.R
import com.example.mytestingproject.databinding.QuestionOneLayoutBinding

class QuestionOneFragment : Fragment(R.layout.question_one_layout) {

    private lateinit var binding: QuestionOneLayoutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = QuestionOneLayoutBinding.bind(view)
    }
}
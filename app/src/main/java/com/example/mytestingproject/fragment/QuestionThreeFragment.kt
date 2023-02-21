package com.example.mytestingproject.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mytestingproject.R
import com.example.mytestingproject.databinding.QuestionThreeLayoutBinding

class QuestionThreeFragment : Fragment(R.layout.question_three_layout) {

    private lateinit var binding: QuestionThreeLayoutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = QuestionThreeLayoutBinding.bind(view)

    }
}
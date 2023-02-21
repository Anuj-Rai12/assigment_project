package com.example.mytestingproject.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mytestingproject.R
import com.example.mytestingproject.databinding.QuestionTwoScreenLayoutBinding
import com.example.mytestingproject.utils.HelperUtils

class QuestionTwoFragment : Fragment(R.layout.question_two_screen_layout) {

    private lateinit var binding: QuestionTwoScreenLayoutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = QuestionTwoScreenLayoutBinding.bind(view)
        binding.btnClick.setOnClickListener {
            shareUrl()
        }
    }

   private fun shareUrl() {
        try {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "VIDEO URL")
            shareIntent.putExtra(Intent.EXTRA_TEXT, HelperUtils.VideoUrl)
            startActivity(Intent.createChooser(shareIntent, "Select the Options"))
        } catch (e: Exception) {
            //e.toString();
        }
    }
}
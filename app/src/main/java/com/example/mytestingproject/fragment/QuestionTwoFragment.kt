package com.example.mytestingproject.fragment

import android.content.Context
import android.content.Intent
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

    fun shareApp(context: Context, shareMessage: String?) {
        try {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            /*shareIntent.putExtra(Intent.EXTRA_SUBJECT,
                    context.getResources().getString(R.string.app_name));*/
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            context.startActivity(Intent.createChooser(shareIntent, "choose one"))
        } catch (e: Exception) {
            //e.toString();
        }
    }
}
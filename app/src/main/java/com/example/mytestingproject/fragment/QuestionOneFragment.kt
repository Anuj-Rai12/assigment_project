package com.example.mytestingproject.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mytestingproject.R
import com.example.mytestingproject.databinding.QuestionOneLayoutBinding
import com.example.mytestingproject.utils.*
import com.example.mytestingproject.viewmodel.QuestionOneViewModel

class QuestionOneFragment : Fragment(R.layout.question_one_layout) {

    private lateinit var binding: QuestionOneLayoutBinding

    private val viewModel: QuestionOneViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = QuestionOneLayoutBinding.bind(view)
        viewModel.event.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { msg ->
                activity?.showDialog("Failed", msg, true) {}
            }
        }
        getMarvelHero()
    }

    private fun getMarvelHero() {
        viewModel.marvelHero.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResponse.Error -> {
                    hide()
                    val err =
                        (it.data as String?) ?: it.exception?.localizedMessage ?: "Unknown Error"
                    activity?.showDialog("Failed", err, true) {}
                }
                is ApiResponse.Loading -> {
                    show("${it.data}")
                }
                is ApiResponse.Success -> {
                    hide()
                    activity?.msg("${it.data}")
                }
            }
        }
    }

    private fun hide() {
        binding.pb.isVisible = false
        binding.resTxt.hide()
    }

    private fun show(msg: String) {
        binding.pb.isVisible = true
        binding.resTxt.text = msg
        binding.resTxt.show()
    }
}
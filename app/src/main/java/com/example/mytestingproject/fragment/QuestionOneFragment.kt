package com.example.mytestingproject.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytestingproject.R
import com.example.mytestingproject.adaptor.HeroAdaptor
import com.example.mytestingproject.databinding.QuestionOneLayoutBinding
import com.example.mytestingproject.fragment.dialog.HeroDetailDialog
import com.example.mytestingproject.model.MarvelHeroes
import com.example.mytestingproject.model.MarvelHeroesItem
import com.example.mytestingproject.utils.*
import com.example.mytestingproject.viewmodel.QuestionOneViewModel
import com.google.android.material.divider.MaterialDividerItemDecoration

class QuestionOneFragment : Fragment(R.layout.question_one_layout) {

    private lateinit var binding: QuestionOneLayoutBinding

    private val viewModel: QuestionOneViewModel by viewModels()

    private lateinit var heroAdaptor: HeroAdaptor


    private val dialog by lazy {
        HeroDetailDialog(requireActivity())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = QuestionOneLayoutBinding.bind(view)
        viewModel.event.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { msg ->
                activity?.showDialog("Failed", msg, true) {}
            }
        }
        setRecycle()
        getMarvelHero()
    }

    private fun setRecycle() {
        binding.recycleView.apply {
            heroAdaptor = HeroAdaptor {
                goToDialog(it)
            }
            val divider =
                MaterialDividerItemDecoration(requireActivity(), LinearLayoutManager.VERTICAL)
            divider.dividerInsetEnd = 20
            divider.dividerInsetStart = 20
            divider.dividerThickness = 2
            addItemDecoration(divider)
            adapter = heroAdaptor
        }
    }

    private fun goToDialog(data: MarvelHeroesItem) {
        dialog.showDialogDetail(data, requireActivity())
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
                    val data = it.data as MarvelHeroes
                    heroAdaptor.submitList(data)
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
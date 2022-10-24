package com.example.mytestingproject.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mytestingproject.R
import com.example.mytestingproject.databinding.KnowledgeLayoutBinding

class KnowledgeBaseFragment :Fragment(R.layout.knowledge_layout) {

    private lateinit var binding: KnowledgeLayoutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= KnowledgeLayoutBinding.bind(view)



    }
}
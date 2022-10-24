package com.example.mytestingproject.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mytestingproject.R
import com.example.mytestingproject.databinding.ChatFragmentLayoutBinding

class ChatSupportFragment:Fragment(R.layout.chat_fragment_layout) {

    private lateinit var binding: ChatFragmentLayoutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= ChatFragmentLayoutBinding.bind(view)



    }
}

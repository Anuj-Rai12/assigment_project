package com.example.mytestingproject.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mytestingproject.R
import com.example.mytestingproject.databinding.LiveChatSupportLayoutBinding
import com.zoho.desk.asap.livechat.ZDPortalLiveChat

class LiveChatFragment : Fragment(R.layout.live_chat_support_layout) {

    private lateinit var binding: LiveChatSupportLayoutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LiveChatSupportLayoutBinding.bind(view)
        ZDPortalLiveChat.show(activity)

    }
}
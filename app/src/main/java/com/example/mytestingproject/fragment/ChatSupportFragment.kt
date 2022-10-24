package com.example.mytestingproject.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mytestingproject.R
import com.example.mytestingproject.databinding.ChatFragmentLayoutBinding
import com.zoho.desk.chat.ZDPortalChat
import com.zoho.desk.chat.ZDPortalChatUser

class ChatSupportFragment : Fragment(R.layout.chat_fragment_layout) {

    private lateinit var binding: ChatFragmentLayoutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ChatFragmentLayoutBinding.bind(view)
        ZDPortalChat.show(activity)
        val chatUser = ZDPortalChatUser()
        chatUser.email = "Anujraiak@outlook.com"
        chatUser.name = "Testing"
        chatUser.phone = "+919219141756"
        ZDPortalChat.setGuestUserDetails(chatUser)
    }
}

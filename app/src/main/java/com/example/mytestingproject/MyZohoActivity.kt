package com.example.mytestingproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mytestingproject.databinding.MyZohoActvityBinding
import com.example.mytestingproject.fragment.*

class MyZohoActivity : AppCompatActivity() {
    private lateinit var binding: MyZohoActvityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MyZohoActvityBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.chat_support -> {
                    val fragment = ChatSupportFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(binding.flFragment.id, fragment).commit()
                }

                R.id.knowledge_base -> {
                    val fragment = KnowledgeBaseFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(binding.flFragment.id, fragment).commit()
                }
                R.id.support_ticket_id -> {
                    val fragment =  SubmitTicketFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(binding.flFragment.id, fragment).commit()
                }
                R.id.ticket_view -> {
                    val fragment = TicketFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(binding.flFragment.id, fragment).commit()
                }
                R.id.live_chat -> {
                    val fragment =LiveChatFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(binding.flFragment.id, fragment).commit()
                }
            }
            return@setOnItemSelectedListener true
        }

        binding.bottomNavigationView.selectedItemId = R.id.chat_support
/*
        val homeScr=ZDPHomeConfiguration.Builder()
            .showKB(true)
            .showMyTickets(true)
            .showCommunity(true)
            .showLiveChat(true)
            .showNavDrawer(true).build()

        ZDPortalHome.show(this,homeScr)*/

    }

}
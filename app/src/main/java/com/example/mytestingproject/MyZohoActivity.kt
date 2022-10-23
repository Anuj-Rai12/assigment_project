package com.example.mytestingproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mytestingproject.databinding.MyZohoActvityBinding
import com.zoho.desk.asap.withchat.ZDPortalHome

class MyZohoActivity : AppCompatActivity() {
    private lateinit var binding: MyZohoActvityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MyZohoActvityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ZDPortalHome.show(this)
    }
}
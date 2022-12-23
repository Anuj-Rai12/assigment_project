package com.example.mytestingproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mytestingproject.databinding.ActivityMainBinding
import com.example.mytestingproject.model.ProfileRequestBody
import com.example.mytestingproject.repo.ApiCallRepo
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    private lateinit var binding :ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        runBlocking {
            val body = ProfileRequestBody(
                deviceName = "INFINIX MOBILITY LIMITED Infinix X688B",
                appPackage = "com.myparkingsindia.gatekeeper",
                accesskey = "9M5P",
                deviceId = "3eb8e470d38312b4",
                deviceType = "Android",
                operatorId = "470e7a4f017a5476afb7eeb3f8b96f9b",
                appVersion = "3.0.1",
            )
            val repo = ApiCallRepo().getItem(body)
            repo.collectLatest {
                binding.txt.text=it.toString()
            }
        }
    }
}
package com.example.mytestingproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mytestingproject.databinding.ActivityMainBinding
import com.example.mytestingproject.utils.Info

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pay.setOnClickListener {
            val deepLink = Info.response
            Log.i("TESTING", "onCreate: $deepLink")
            val intent =packageManager.getLaunchIntentForPackage(Info.paytmPackage)
            intent?.let {
                intent.putExtra("deeplink",deepLink)
                startActivity(intent)
            }
        }

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent!=null && intent.hasExtra("deeplink")){
            val dl = intent.getStringExtra("deeplink")
            Log.i("TESTING", "onNewIntent:Response $dl")
        }
    }

}
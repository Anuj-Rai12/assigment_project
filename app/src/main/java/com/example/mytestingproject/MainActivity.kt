package com.example.mytestingproject

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mytestingproject.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.paxStore.setOnClickListener {
            val launchIntent =
                packageManager.getLaunchIntentForPackage("com.pax.market.android.app")

            if (launchIntent != null) {
                startActivity(launchIntent)
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "There is no package available in android",
                    Toast.LENGTH_LONG
                ).show()
            }

        }

    }
}
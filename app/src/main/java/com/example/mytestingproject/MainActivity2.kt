package com.example.mytestingproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.mytestingproject.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding:ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val fragment: Fragment = MapsFragmentTwo()

        supportFragmentManager
            .beginTransaction().replace(R.id.frame_layout, fragment)
            .commit()

    }
}
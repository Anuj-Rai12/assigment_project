package com.example.mytestingproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mytestingproject.databinding.ActivityMainBinding
import com.example.mytestingproject.tabs.OneFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adaptor: ViewPagerStateAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adaptor = ViewPagerStateAdaptor(supportFragmentManager)
        adaptor.addFragment(OneFragment(1, R.color.purple_200))
        adaptor.addFragment(OneFragment(2, R.color.teal_700))
        adaptor.addFragment(OneFragment(3, R.color.teal_200))
        adaptor.addFragment(OneFragment(4, R.color.purple_700))
        binding.pager.adapter = adaptor
        binding.pager.setPageTransformer(true,HingeAnimation())

    }
}
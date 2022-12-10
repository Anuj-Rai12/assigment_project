package com.example.mytestingproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.mytestingproject.adaptor.MainViewPager
import com.example.mytestingproject.databinding.ActionBarLayoutBinding
import com.example.mytestingproject.databinding.ActivityMainBinding
import com.example.mytestingproject.fragment.OneFragment
import com.example.mytestingproject.utils.msg
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPager: MainViewPager
    private val stringArr = listOf("My FDs", "Invest")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setActionBar()
        viewPager = MainViewPager(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = viewPager
        viewPager.setFragment(OneFragment())
        viewPager.setFragment(OneFragment())
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, pos ->
            tab.text = stringArr[pos]
        }.attach()

    }

    @SuppressLint("RtlHardcoded")
    private fun setActionBar() {
        val binding = ActionBarLayoutBinding.inflate(layoutInflater)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        val lp: ActionBar.LayoutParams =
            ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT
            )
        lp.gravity = Gravity.LEFT
        supportActionBar!!.setCustomView(binding.root, lp)
        binding.root.setContentInsetsAbsolute(0, 0)
        binding.backBtn.setOnClickListener {
            msg("back arrow")
        }
        binding.infoBtn.setOnClickListener {
            msg("Information")
        }

    }
}
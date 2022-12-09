package com.example.mytestingproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.mytestingproject.databinding.ActionBarLayoutBinding
import com.example.mytestingproject.databinding.ActivityMainBinding
import com.example.mytestingproject.utils.changeStatusBarColor
import com.example.mytestingproject.utils.msg


class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        changeStatusBarColor()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setActionBar()
    }

    @SuppressLint("RtlHardcoded")
    private fun setActionBar() {
        val binding=ActionBarLayoutBinding.inflate(layoutInflater)

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
        binding.toolbarBackBtn.setOnClickListener {
            msg("back arrow")
        }
    }
}
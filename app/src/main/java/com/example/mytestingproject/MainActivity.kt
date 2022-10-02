package com.example.mytestingproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mytestingproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnClick.setOnClickListener {
            getToAnother()
        }

    }
private fun getToAnother(){
    val intent=Intent(this,TwoActivity::class.java)
    startActivity(intent)
    finish()
}

}
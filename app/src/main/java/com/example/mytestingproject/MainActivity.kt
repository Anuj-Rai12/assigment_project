package com.example.mytestingproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mytestingproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    companion object{
        var intented:Intent?=null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        intented=intent

    }

    /*
private fun getToAnother(){
    val intent=Intent(this,TwoActivity::class.java)
    startActivity(intent)
    finish()
}*/

}
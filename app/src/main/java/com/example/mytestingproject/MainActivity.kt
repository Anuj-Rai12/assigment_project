package com.example.mytestingproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mytestingproject.databinding.ActivityMainBinding
import com.zoho.desk.asap.api.ZDPortalCallback
import com.zoho.desk.asap.api.ZDPortalException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        const val userToken = "d8f1d3182f9f24a870af340fd9bb7685ef276498988364ff008491700dd365c2"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val flag = MainApplication.apiProvider?.isUserSignedIn
        flag?.let {
            if (!it) {
                doLogin()
            } else {
                toastMsg("user is already signed In")
            }
        } ?: run {
            toastMsg("user signed is null")
        }
        binding.goTo.setOnClickListener {
            startActivity(Intent(this, MyZohoActivity::class.java))
        }
    }

    private fun doLogin() {
        MainApplication.apiProvider?.setUserToken(userToken,
            object : ZDPortalCallback.SetUserCallback {
                override fun onException(exp: ZDPortalException?) {
                    toastMsg("Error")
                    setLogCat("SubmitTicket", exp?.errorMsg ?: "Unknown Error")
                }

                override fun onUserSetSuccess() {
                    toastMsg("user is Logged In")
                }

            })
    }
}
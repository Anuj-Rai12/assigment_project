package com.example.mytestingproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mytestingproject.databinding.ActivityMainBinding
import com.zoho.desk.asap.api.ZDPortalCallback
import com.zoho.desk.asap.api.ZDPortalException

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val flag=MainApplication.apiProvider?.isUserSignedIn
        flag?.let {
            if (!it){
                doLogin()
            }else{
                toastMsg("user is already signed In")
            }
        }?: run {
            toastMsg("user signed is null")
        }
        binding.goTo.setOnClickListener {
            startActivity(Intent(this,MyZohoActivity::class.java))
        }
    }

    private fun doLogin() {
        MainApplication.apiProvider?.setUserToken("Anujraiak@outlook.com",object :ZDPortalCallback.SetUserCallback{
            override fun onException(exp: ZDPortalException?) {
                toastMsg("Error")
                setLogCat("SubmitTicket",exp?.errorMsg?:"Unknown Error")
            }

            override fun onUserSetSuccess() {
                toastMsg("user is Logged In")
            }

        })
    }
}
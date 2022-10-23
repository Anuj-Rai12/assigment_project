package com.example.mytestingproject

import android.annotation.SuppressLint
import android.app.Application
import com.zoho.desk.asap.api.ZohoDeskPortalSDK
import com.zoho.desk.asap.api.ZohoDeskPortalSDK.DataCenter

class MainApplication :Application(){
    companion object{
        @SuppressLint("StaticFieldLeak")
        var apiProvider:ZohoDeskPortalSDK?=null
    }

    private val orgId=60017573448
    private val appId="edbsnef8a4b8c4b2a0a450193cbd9b43ed9632398151bdb00c05f4d30dbf866888687"
    private val datacenterValue=DataCenter.IN

    override fun onCreate() {
        super.onCreate()
        ZohoDeskPortalSDK.Logger.enableLogs()
        apiProvider= ZohoDeskPortalSDK.getInstance(applicationContext)
        apiProvider?.initDesk(orgId,appId,datacenterValue)
    }
}
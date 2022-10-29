package com.example.mytestingproject

import android.util.Log
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.HttpURLConnection
import java.net.URL

class GetPdfResponse {

    fun getPdfResponse(url:String)=flow{
        try {
            val u = URL(url)
            val c = u.openConnection() as HttpURLConnection
            c.requestMethod = "GET"
            c.connect()
            emit(c.inputStream)
        }catch (e:Exception){
            Log.i("PDF_LOADING", "getPdfResponse: ${e.localizedMessage}")
            emit(null)
        }
    }.flowOn(IO)
}
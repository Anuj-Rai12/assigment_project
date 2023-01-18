package com.example.mytestingproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mytestingproject.databinding.ActivityMainBinding
import com.example.mytestingproject.utils.Info
import com.example.mytestingproject.utils.PrintFile
import com.paytm.printgenerator.printer.PrintStatusCallBack
import com.paytm.printgenerator.printer.PrintStatusEnum
import com.paytm.printgenerator.printer.Printer

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val callBack = object : PrintStatusCallBack {
            override fun onFailure(id: String, status: PrintStatusEnum) {
                Toast.makeText(this@MainActivity, status.getMessage(), Toast.LENGTH_SHORT).show()
                Log.i("PRINTING", "onFailure: $status")
            }

            override fun onSuccess(id: String) {
                Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
            }
        }

        binding.print.setOnClickListener {
            Thread().run {
                val print=PrintFile(this@MainActivity)
                val page=print.checkIn()
                if (page!=null){
                    Log.i("PRINTING", "onCreate: Testing ....")
                    Printer.print(page, "temp",this@MainActivity, callBack)
                }else{
                    Toast.makeText(this@MainActivity, "Page is Null", Toast.LENGTH_SHORT).show()
                }
            }
        }




        binding.pay.setOnClickListener {
            val deepLink = Info.response
            Log.i("TESTING", "onCreate: $deepLink")
            val intent = packageManager.getLaunchIntentForPackage(Info.paytmPackage)
            intent?.let {
                intent.putExtra("deeplink", deepLink)
                startActivity(intent)
            }
        }


    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null && intent.hasExtra("deeplink")) {
            val dl = intent.getStringExtra("deeplink")
            Log.i("TESTING", "onNewIntent:Response $dl")
        }
    }

}
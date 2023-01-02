package com.example.mytestingproject

import android.os.Bundle
import com.example.mytestingproject.databinding.ActivityMainBinding
import com.example.mytestingproject.pine.AppConfig
import com.example.mytestingproject.pine.BasePineActivity
import com.example.mytestingproject.pine.PineServiceHelper
import com.example.mytestingproject.pine.request.Detail
import com.example.mytestingproject.pine.request.Header
import com.example.mytestingproject.pine.request.TransactionRequest
import com.example.mytestingproject.pine.response.TransactionResponse
import com.example.mytestingproject.unit.Utils

class PineTestingActivity : BasePineActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val psh = PineServiceHelper.getInstance()
        psh.connect(object : PineServiceHelper.PineCallBack {
            override fun showToast(msg: String?) {
                Utils.createLogcat("PINE_Res", "$msg")
            }

            override fun connectAgain() {
                Utils.createLogcat("PINE_Res", "Connect Again")
            }

            override fun sendResult(detailResponse: TransactionResponse?) {
                Utils.createLogcat("PINE_Res", "$detailResponse")
            }

            override fun showWaitingDialog() {
                Utils.createLogcat("PINE_Res", "Waiting...")
            }

        }, this)



        binding.amtByCard.setOnClickListener {
            val amt = 20

            val request = TransactionRequest()
            request.aPP_ID = AppConfig.APP_ID
            //Setting header ;
            val header=Header()
            header.applicationId = AppConfig.APP_ID
            header.methodId = "1001"
            header.userId = "AnujRai101"
            header.versionNo = AppConfig.versionCode
            val detail=Detail()
            detail.data = arrayListOf()
            request.header=header

            //Detail Obj
            detail.billingRefNo = "receiptNo1010"
            detail.paymentAmount = (amt * 100.0).toString()
            detail.transactionType = "4001"//Card
            detail.mobileNumberForEChargeSlip = "9219141756"
            request.detail=detail

            psh.callPineService(request)

        }



        binding.amtByQr.setOnClickListener {

            val amt = 20

            val request = TransactionRequest()
            request.aPP_ID = AppConfig.APP_ID
            //Setting header ;
            val header=Header()
            header.applicationId = AppConfig.APP_ID
            header.methodId = "1001"
            header.userId = "AnujRai101"
            header.versionNo = AppConfig.versionCode
            val detail=Detail()
            detail.data = arrayListOf()
            request.header=header

            //Detail Obj
            detail.billingRefNo = "receiptNo1010"
            detail.paymentAmount = (amt * 100.0).toString()
            detail.transactionType = "4006"//Qr
            detail.mobileNumberForEChargeSlip = "9219141756"
            request.detail=detail

            psh.callPineService(request)


        }


    }
}
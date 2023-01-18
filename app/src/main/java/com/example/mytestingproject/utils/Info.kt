package com.example.mytestingproject.utils

object Info {

    val paytmPackage = "com.paytm.pos.debug"
    val packName = "com.example.mytestingproject"
    val callbackAction = "com.paytm.pos.payment.CALL_BACK_RESULT"
    val orderId = "123243443"
    val paymentMode = "All"
    val paymentDI = "sampledeeplink://payment"
    val amount = "1200"

    val response = "paytmedc://paymentV2?" +
            "callbackAction=" + callbackAction +
            "&callbackPkg=" + packName +
            "&amount=" + amount +
            "&orderId=" + orderId +
            "&requestPayMode=" + paymentMode +
            "&callbackDl=" + paymentDI

}
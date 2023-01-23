package com.example.mytestingproject.utils

object Info {

    val paytmPackage = "com.paytm.pos.debug"
    val packName = "com.example.mytestingproject"
    val callbackAction = "com.paytm.pos.payment.CALL_BACK_RESULT"
    val paymentMode = "All"
    val paymentDI = "sampledeeplink://payment"
    val amount = "100"

    val response = "paytmedc://paymentV2?" +
            "callbackAction=" + callbackAction +
            "&callbackPkg=" + packName +
            "&amount=" + amount +
            "&orderId=" + "123243"+(100..1000).random() +
            "&requestPayMode=" + paymentMode +
            "&callbackDl=" + paymentDI+
            "&param1=OldThing Completed&param2=fdjlgdkljsdgl"

}
package com.example.mytestingproject.model




data class ProfileRequestBody(
    val accesskey: String,
     val appPackage: String,
     val appVersion: String,
     val deviceId: String,
     val deviceName: String,
     val deviceType: String,
     val operatorId: String
){
    fun fromDataToList(): String {
        val stringBuilder=StringBuilder()
        stringBuilder.append("Accesskey=${this.accesskey}&")
        stringBuilder.append("App_Package=${this.appPackage}&")
        stringBuilder.append("Device_Name=${this.deviceName}&")
        stringBuilder.append("Device_Id=${this.deviceId}&")
        stringBuilder.append("Device_Type=${this.deviceType}&")
        stringBuilder.append("App_Version=${this.appVersion}&")
        stringBuilder.append("Operator_Id=${this.operatorId}")
        return  stringBuilder.toString()
    }

}
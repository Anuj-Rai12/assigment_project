package com.example.mytestingproject.repo

import android.util.Log
import com.example.mytestingproject.api.APInstance
import com.example.mytestingproject.model.OperatorID
import com.example.mytestingproject.model.ProfileRequestBody
import com.example.mytestingproject.utils.Headers
import com.example.mytestingproject.utils.deserializeFromJson
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MediaType.Companion.toMediaTypeOrNull

class ApiCallRepo {

    fun getItem(
        profileRequestBody: ProfileRequestBody,
        timeStamp: String = "9b12778a03c745bac897d509328c6e75"
    ) = flow {
        emit("Loading ..")
        val data = try {
            val strBody = profileRequestBody.fromDataToList()
            val requestBody =
                APInstance.createRequestBody(Headers.xxxUrlCode.toMediaTypeOrNull(), strBody)
            val header = hashMapOf<String, String>()
            header["Timestamp"] = timeStamp
            header["Content-Type"] = Headers.xxxUrlCode

            val response = APInstance().getResponse(
                url = Headers.profileUrl,
                method = Pair(APInstance.Companion.TYPE.POST.name, requestBody),
                header
            )
            // val value = GsonUtils.fromJsonToString(response
            //  val response=APInstance().response
/*            Log.i("API_RES", "getItem: Item->  $response")
            Log.i("API_RES", "getItem: Body->  ${response.peekBody(2048).string()}")*/

            if (response.isSuccessful && response.body != null)
                deserializeFromJson<OperatorID>(response.body?.string()) ?: "cannot find data"
            else "Failed"

        } catch (e: Exception) {
            Log.i("API_RES", "getItem: ${e.localizedMessage}")
            e.localizedMessage
        }
        emit(data)
    }.flowOn(IO)


}
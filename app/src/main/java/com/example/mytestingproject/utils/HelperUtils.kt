package com.example.mytestingproject.utils

import android.app.Activity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.navigation.NavController
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit

fun NavController.safeNavigate(actionId: Int) {
    currentDestination?.getAction(actionId)?.run {
        navigate(actionId)
    }
}

inline fun <reified T> buildApi(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}

fun getEmojiByUnicode(unicode: Int) = String(Character.toChars(unicode))

object HelperUtils {
    const val apiBaseUrl = "https://simplifiedcoding.net/demos/"
    const val EndPointUrl = "marvel"
}

fun View.hide() {
    this.isVisible = false
}


fun Activity.msg(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun View.show() {
    this.isVisible = true
}


inline fun Activity.showDialog(
    type: String = "Failed", msg: String, isCancelAble: Boolean = true,
    crossinline callBack: () -> Unit,
) {
    val dialog = AlertDialog.Builder(this)
    dialog.setTitle(type)
    dialog.setMessage(msg)
    dialog.setCancelable(isCancelAble)
    dialog.setPositiveButton("ok") { it, _ ->
        it.dismiss()
        callBack.invoke()
    }
    dialog.setOnDismissListener {
        callBack.invoke()
    }
    dialog.show()
}
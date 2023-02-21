package com.example.mytestingproject.utils

import android.app.Activity
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
import androidx.navigation.NavController
import com.example.mytestingproject.R
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
    const val VideoUrl="http://pandora.yilstaging.com/writable/uploads/20210127/1611811599_2ac19cd41e8387119d7e.mp3"
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


fun setHtmlTxt(txt: String, color: String): Spanned {
    return if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
        Html.fromHtml(
            "<font color=$color>$txt</font>", HtmlCompat.FROM_HTML_MODE_COMPACT
        )
    } else {
        Html.fromHtml("<font color='$color'>$txt</font>")
    }
}

fun Activity.changeStatusBarColor(color: Int = R.color.bg_top) {
    this.window?.statusBarColor = getColorInt(color)
}

fun Activity.getColorInt(color: Int): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        resources.getColor(color, null)
    } else {
        resources.getColor(color)
    }
}
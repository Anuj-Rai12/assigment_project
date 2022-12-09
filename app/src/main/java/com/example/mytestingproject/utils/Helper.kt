package com.example.mytestingproject.utils

import android.app.Activity
import android.content.Context
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.widget.Toast
import androidx.core.text.HtmlCompat

const val Ruppess = "₹"


fun Activity.getColorInt(color: Int): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        resources.getColor(color, null)
    } else {
        resources.getColor(color)
    }
}

fun Context.msg(string: String, time: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, string, time).show()
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

val InfoList = listOf(
    "More about NRO FD on SBNRI",
    "About NRO Fixed Deposit",
    "Why should I invest in NRO FD?",
    "Safety & credibility",
    "Risks & return",
    "How to start?",
    "Mode of investment & payment",
    "Taxation on FD Earnings",
    "Repatriation outside India",
    "Pre-mature withdrawal",
    "What happens at maturity?",
    "Talk to SBNRI Advisor"
)


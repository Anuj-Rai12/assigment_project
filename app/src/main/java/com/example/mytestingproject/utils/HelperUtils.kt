package com.example.mytestingproject.utils

import androidx.navigation.NavController

fun NavController.safeNavigate(actionId:Int) {
    currentDestination?.getAction(actionId)?.run {
        navigate(actionId)
    }
}

object HelperUtils {
    const val apiBaseUrl = "https://simplifiedcoding.net/demos/"
    const val EndPointUrl = "marvel"
}
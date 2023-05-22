package com.cpe.util

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log

fun getAppVersion(context: Context): Pair<String, Int> {
    var versionName = ""
    var versionCode = 0

    try {
        val packageInfo =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                context.packageManager.getPackageInfo(
                    context.packageName,
                    PackageManager.PackageInfoFlags.of(0)
                )
            } else context.packageManager.getPackageInfo(
                context.packageName,
                0
            )
        versionName = packageInfo.versionName
        versionCode =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                packageInfo.longVersionCode.toInt()
            } else {
                packageInfo.versionCode
            }
    } catch (e: Exception) {
        Log.d("getAppVersion", "getAppVersion: ${e.message}")
    }

    return Pair(versionName, versionCode)
}
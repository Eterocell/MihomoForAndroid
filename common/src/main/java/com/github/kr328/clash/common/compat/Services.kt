package com.github.kr328.clash.common.compat

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.ServiceCompat
import androidx.core.app.ServiceCompat.StopForegroundFlags

fun Context.startForegroundServiceCompat(intent: Intent) {
    if (Build.VERSION.SDK_INT >= 26) {
        startForegroundService(intent)
    } else {
        startService(intent)
    }
}

fun Service.stopForegroundCompat(@StopForegroundFlags flags: Int) {
    ServiceCompat.stopForeground(this, flags)
}

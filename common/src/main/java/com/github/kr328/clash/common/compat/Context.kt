package com.github.kr328.clash.common.compat

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun Context.getColorCompat(
    @ColorRes id: Int,
): Int = ContextCompat.getColor(this, id)

fun Context.getDrawableCompat(
    @DrawableRes id: Int,
): Drawable? = ContextCompat.getDrawable(this, id)

fun PackageManager.getPackageInfoCompat(
    packageName: String,
    flags: Int,
): PackageInfo =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(flags.toLong()))
    } else {
        getPackageInfo(packageName, flags)
    }

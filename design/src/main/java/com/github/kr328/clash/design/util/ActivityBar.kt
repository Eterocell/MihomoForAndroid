package com.github.kr328.clash.design.util

import android.app.Activity
import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.github.kr328.clash.design.R
import com.github.kr328.clash.design.view.ActivityBarLayout

fun ActivityBarLayout.applyFrom(context: Context) {
    when (context) {
        is ComponentActivity -> {
            findViewById<ImageView>(R.id.activity_bar_close_view)?.apply {
                setOnClickListener {
                    context.onBackPressedDispatcher.onBackPressed()
                }
            }
            findViewById<TextView>(R.id.activity_bar_title_view)?.apply {
                text = context.title
            }
        }

        is Activity -> {
            throw IllegalStateException("All activities should be or inherit androidx.activity.ComponentActivity")
        }
    }
}

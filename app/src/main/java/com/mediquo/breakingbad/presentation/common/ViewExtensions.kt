package com.mediquo.breakingbad.presentation.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.mediquo.breakingbad.R

fun AppCompatActivity.handleException(
    exception: Exception,
    cancel: () -> Unit = {},
    retry: () -> Unit = {}
) {
    AlertDialog.Builder(this)
        .setMessage(exception.message)
        .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
            dialog.dismiss()
            cancel()
        }
        .setPositiveButton(getString(R.string.retry)) { dialog, _ ->
            dialog.dismiss()
            retry()
        }
        .setCancelable(false)
        .create()
        .show()
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}
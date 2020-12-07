package com.mediquo.breakingbad.presentation.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.mediquo.breakingbad.R
import com.mediquo.breakingbad.domain.exception.NetworkConnectionException
import com.mediquo.breakingbad.domain.exception.ServiceException

fun AppCompatActivity.handleException(
    exception: Exception,
    cancel: () -> Unit = {},
    retry: () -> Unit = {}
) {
    val message: String = when(exception) {
        is NetworkConnectionException -> getString(R.string.network_exception_message)
        is ServiceException -> getString(R.string.service_exception_message)
        else -> getString(R.string.unknown_exception_message)
    }

    AlertDialog.Builder(this)
        .setMessage(message)
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
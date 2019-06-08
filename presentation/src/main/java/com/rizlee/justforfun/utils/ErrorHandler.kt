package com.rizlee.justforfun.utils

import android.content.Context
import androidx.annotation.StringRes
import com.rizlee.justforfun.R

class ErrorHandler(
        private val context: Context,
        private val errorHandlerCallBack: ErrorHandlerCallback
) {

    fun handleError(throwable: Throwable, action: (() -> Unit)? = null) {
        when (throwable) {
            is Exception -> onUnknownException(action)
            else -> onUnknownException(action)
        }
    }

    private fun onUnknownException(action: (() -> Unit)?) {
        errorCatchEvent(R.string.error_unhandled_error, action)
    }

    private fun errorCatchEvent(@StringRes resId: Int, action: (() -> Unit)?) {
        errorHandlerCallBack.showErrorMessage(context.getString(resId), action)
    }

    private fun errorCatchEvent(errorStr: String?, action: (() -> Unit)?) {
        errorStr?.let { errorHandlerCallBack.showErrorMessage(it, action) }
    }

    interface ErrorHandlerCallback {
        fun showErrorMessage(message: String, action: (() -> Unit)?)
    }
}
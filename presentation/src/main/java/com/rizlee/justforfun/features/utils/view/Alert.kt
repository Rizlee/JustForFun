package com.rizlee.justforfun.features.utils.view

import android.app.AlertDialog
import android.content.Context
import com.rizlee.justforfun.R

class Alert {

    var dialog: AlertDialog? = null

    fun showDialog(alertBody: AlertBody, context: Context) {
       dialog = AlertDialog.Builder(context, R.style.AppAlertDialog).run {
            alertBody.messageRes?.let { setTitle(it) }
            alertBody.messageStr?.let { setTitle(it) }

            if (alertBody.positiveAction == null) {
                setPositiveButton(R.string.all_ok) { _, _ ->  dialog?.dismiss()}
            }

           alertBody.positiveAction?.let {
                var positiveRes = R.string.all_ok
               alertBody.positiveRes?.let {
                    positiveRes = it
                }

                setPositiveButton(positiveRes) { _, _ ->
                    it.invoke()
                    dialog?.dismiss()
                }
            }

           alertBody.negativeAction?.let {
                var negativeRes = R.string.all_cancel
               alertBody.negativeRes?.let {
                    negativeRes = it
                }

                setNegativeButton(negativeRes) { _, _ ->
                    it.invoke()
                    dialog?.dismiss()
                }
            }
            setCancelable(false)
            create()
           show()
        }
    }

    fun dismiss() = dialog?.dismiss()

    class AlertBody constructor(val messageRes: Int? = null,
                                val messageStr: String? = null,
                                val positiveRes: Int? = null,
                                val negativeRes: Int? = null,
                                val positiveAction: (() -> Unit)? = null,
                                val negativeAction: (() -> Unit)? = null)
}
package com.rizlee.justforfun.features.base

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rizlee.justforfun.R
import com.rizlee.justforfun.features.utils.lifecycle.Event
import com.rizlee.justforfun.features.utils.lifecycle.SingleLiveEvent
import com.rizlee.justforfun.features.utils.view.Alert
import com.rizlee.justforfun.utils.ErrorHandler
import javax.inject.Inject

abstract class BaseViewModel : ViewModel(), ErrorHandler.ErrorHandlerCallback {

    @Inject
    protected lateinit var context: Context

    private val errorHandler by lazy { ErrorHandler(context, this) }

    val newActivityIntentEvent = MutableLiveData<Event<Intent>>()
    val finishEvent = MutableLiveData<Event<Unit>>()
    val dialogEvent = SingleLiveEvent<Alert.AlertBody>()

    override fun onCleared() {
        super.onCleared()
        dispose()
        clearComponent()
    }

    protected fun showActivity(intent: Intent) {
        newActivityIntentEvent.value = Event(intent)
    }

    protected fun showDialog(alertBody: Alert.AlertBody) {
        dialogEvent.value = alertBody
    }

    override fun showErrorMessage(message: String, action: (() -> Unit)?) {
        showDialog(Alert.AlertBody(messageStr = message, negativeAction = action, negativeRes = R.string.all_retry))
    }

    protected fun finish() {
        finishEvent.value = Event(Unit)
    }

    protected fun handleError(t: Throwable, action: (() -> Unit)? = null) =
            errorHandler.handleError(t, action)

    abstract fun onViewCreated()

    abstract fun clearComponent()

    abstract fun dispose()
}
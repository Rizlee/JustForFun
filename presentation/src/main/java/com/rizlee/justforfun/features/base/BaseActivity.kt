package com.rizlee.justforfun.features.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.rizlee.justforfun.features.utils.lifecycle.EventObserver
import com.rizlee.justforfun.features.utils.view.Alert
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory
    protected lateinit var viewModel: VM

    private val dialogs = mutableListOf<Alert>()

    protected abstract fun injectComponent()

    protected abstract fun injectViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        injectComponent()

        super.onCreate(savedInstanceState)

        injectViewModel()

        viewModel.run {
            onViewCreated()

            newActivityIntentEvent.observe(this@BaseActivity, EventObserver { startActivity(it) })

            dialogEvent.observe(this@BaseActivity, Observer {
                val alert = Alert()
                alert.showDialog(it, this@BaseActivity)
                dialogs.add(alert)
            })

            finishEvent.observe(this@BaseActivity, Observer { finish() })
        }
    }

    protected inline fun <reified T : ViewModel> getViewModel(): T {
        return ViewModelProviders.of(this, viewModelFactory)[T::class.java]
    }

    override fun onStop() {
        for (dialog in dialogs) {
            dialog.dismiss()
        }
        dialogs.clear()
        super.onStop()
    }

    override fun onDestroy() {
        for (dialog in dialogs) {
            dialog.dismiss()
        }

        dialogs.clear()
        super.onDestroy()
    }
}
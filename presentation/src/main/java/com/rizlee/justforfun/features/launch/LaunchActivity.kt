package com.rizlee.justforfun.features.launch

import android.content.Context
import android.content.Intent
import com.rizlee.justforfun.di.Injector
import com.rizlee.justforfun.features.base.BaseActivity

class LaunchActivity : BaseActivity<LaunchViewModel>() {

    companion object {
        internal fun newActivityIntent(context: Context) = Intent(context, LaunchActivity::class.java)
    }

    override fun injectViewModel() {
        viewModel = getViewModel()
    }

    override fun injectComponent() = Injector.plusLaunchComponent().inject(this)
}
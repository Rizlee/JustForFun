package com.rizlee.justforfun.features.launch

import com.rizlee.justforfun.features.base.BaseViewModel
import com.rizlee.justforfun.di.Injector
import com.rizlee.justforfun.features.main.MainActivity
import javax.inject.Inject

class LaunchViewModel @Inject constructor() : BaseViewModel() {

    override fun dispose() = Unit

    override fun clearComponent() = Injector.clearLaunchComponent()

    override fun onViewCreated() = startNextActivity()

    private fun startNextActivity() {
        showActivity(MainActivity.newActivityIntent(context))
        finish()
    }
}
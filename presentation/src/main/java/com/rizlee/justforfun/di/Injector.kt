package com.rizlee.justforfun.di

import android.app.Application
import com.rizlee.justforfun.di.components.AppComponent
import com.rizlee.justforfun.di.components.DaggerAppComponent
import com.rizlee.justforfun.di.components.LaunchComponent
import com.rizlee.justforfun.di.components.MainComponent
import com.rizlee.justforfun.di.modules.AppModule

object Injector {

    private var appComponent: AppComponent? = null

    private var launchComponent: LaunchComponent? = null
    private var mainComponent: MainComponent? = null

    fun initializeAppComponent(application: Application) {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(application))
                .build()
    }

    fun plusLaunchComponent() =
            launchComponent ?: appComponent?.plusLaunchComponent().also { launchComponent = it }!!

    fun clearLaunchComponent() {
        launchComponent = null
    }

    fun plusMainComponent() =
            mainComponent ?: appComponent?.plusMainComponent().also { mainComponent = it }!!

    fun clearMainComponent() {
        mainComponent = null
    }

}
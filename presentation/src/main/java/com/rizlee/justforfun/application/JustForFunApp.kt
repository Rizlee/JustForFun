package com.rizlee.justforfun.application

import android.app.Application
import com.rizlee.justforfun.di.Injector

class JustForFunApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Injector.initializeAppComponent(this)
    }

}
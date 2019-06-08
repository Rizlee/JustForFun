package com.rizlee.justforfun.di.components

import com.rizlee.justforfun.di.modules.AppModule
import com.rizlee.justforfun.di.modules.RestModule
import com.rizlee.justforfun.di.modules.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RestModule::class, ViewModelModule::class])
interface AppComponent {

    fun plusLaunchComponent(): LaunchComponent

    fun plusMainComponent(): MainComponent

}
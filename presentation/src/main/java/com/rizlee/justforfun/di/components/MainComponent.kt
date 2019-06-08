package com.rizlee.justforfun.di.components

import com.rizlee.justforfun.di.modules.MainModule
import com.rizlee.justforfun.features.main.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    fun inject(activity: MainActivity)

}
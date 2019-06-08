package com.rizlee.justforfun.di.components

import com.rizlee.justforfun.di.modules.LaunchModule
import com.rizlee.justforfun.features.launch.LaunchActivity
import dagger.Subcomponent

@Subcomponent(modules = [LaunchModule::class])
interface LaunchComponent {

    fun inject(activity: LaunchActivity)

}
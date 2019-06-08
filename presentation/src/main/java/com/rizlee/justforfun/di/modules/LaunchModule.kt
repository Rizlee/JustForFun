package com.rizlee.justforfun.di.modules

import androidx.lifecycle.ViewModel
import com.rizlee.justforfun.di.annotations.ViewModelKey
import com.rizlee.justforfun.features.launch.LaunchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface LaunchModule {

    @Binds
    @IntoMap
    @ViewModelKey(LaunchViewModel::class)
    fun bindLaunchViewModel(viewModel: LaunchViewModel): ViewModel

}
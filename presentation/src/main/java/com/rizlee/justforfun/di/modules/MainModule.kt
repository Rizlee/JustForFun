package com.rizlee.justforfun.di.modules

import androidx.lifecycle.ViewModel
import com.rizlee.justforfun.di.annotations.ViewModelKey
import com.rizlee.justforfun.features.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindLaunchViewModel(viewModel: MainViewModel): ViewModel

}
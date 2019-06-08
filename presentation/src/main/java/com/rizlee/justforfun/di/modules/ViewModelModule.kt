package com.rizlee.justforfun.di.modules

import androidx.lifecycle.ViewModelProvider
import com.rizlee.justforfun.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory):
            ViewModelProvider.Factory

}
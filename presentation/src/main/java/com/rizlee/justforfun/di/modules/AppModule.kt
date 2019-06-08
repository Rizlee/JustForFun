package com.rizlee.justforfun.di.modules

import android.content.Context
import com.google.gson.Gson
import com.rizlee.data.repository.Repository
import com.rizlee.data.threads.JobExecutor
import com.rizlee.domain.executor.ExecutionThread
import com.rizlee.domain.executor.PostExecutionThread
import com.rizlee.domain.repository.IRepository
import com.rizlee.justforfun.threads.UIThread
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [(AppModule.InnerAppModule::class)])
class AppModule(private var appContext: Context) {

    @Provides
    @Singleton
    fun provideContext() = appContext

    @Module
    interface InnerAppModule {

        @Binds
        @Singleton
        fun provideExecutionThread(jobExecutor: JobExecutor): ExecutionThread

        @Binds
        @Singleton
        fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread

        @Binds
        @Singleton
        fun provideIRepository(repository: Repository): IRepository
    }

}
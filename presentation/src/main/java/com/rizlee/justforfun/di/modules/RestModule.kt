package com.rizlee.justforfun.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rizlee.data.network.api.AphorismApi
import com.rizlee.data.network.api.WeatherApi
import com.rizlee.justforfun.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

private const val URL_APHORISM = " https://api.forismatic.com/"
private const val URL_WEATHER = "http://api.apixu.com/"

@Module
class RestModule {

    @Provides
    @Singleton
    @Named("APHORISM")
    fun provideRestApiAphorism(@Named("APHORISM") retrofit: Retrofit) = retrofit.create(AphorismApi::class.java)

    @Provides
    @Singleton
    @Named("WEATHER")
    fun provideRestApiWeather(@Named("WEATHER") retrofit: Retrofit) = retrofit.create(WeatherApi::class.java)

    @Provides
    @Singleton
    @Named("APHORISM")
    fun provideRetrofitAphorism(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(URL_APHORISM)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    @Named("WEATHER")
    fun provideRetrofitWeather(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(URL_WEATHER)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    @Provides
    @Singleton
    fun provideLogging(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        )

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setPrettyPrinting().create()
}
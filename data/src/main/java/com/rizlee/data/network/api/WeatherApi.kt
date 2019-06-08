package com.rizlee.data.network.api

import com.rizlee.domain.entities.WeatherBody
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("v1/current.json")
    fun getWeather(@Query("key") apiKey: String,
                   @Query("q") cityName: String): Single<WeatherBody>

}
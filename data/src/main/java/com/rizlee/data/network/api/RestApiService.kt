package com.rizlee.data.network.api

import javax.inject.Inject
import javax.inject.Named

private const val WEATHER_API_KEY = "7ce03cf220fe4a499d6105655190806"

class RestApiService @Inject constructor(@Named("APHORISM") private val aphorismApi: AphorismApi,
                                         @Named("WEATHER") private val weatherApi: WeatherApi){

    internal fun getRandomAphorism() = aphorismApi.getRandomAphorism("getQuote", "json", "ru")

    internal fun getWeather(cityName: String) = weatherApi.getWeather(WEATHER_API_KEY, cityName)

}
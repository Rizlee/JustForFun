package com.rizlee.domain.entities

import com.google.gson.annotations.SerializedName

data class WeatherBody(@field:SerializedName("current")
                  var body: WeatherCurrentBody? = null)
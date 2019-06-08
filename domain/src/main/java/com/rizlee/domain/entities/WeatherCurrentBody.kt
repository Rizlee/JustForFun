package com.rizlee.domain.entities

import com.google.gson.annotations.SerializedName

data class WeatherCurrentBody(@field:SerializedName("temp_c")
                              var temperature: String? = null)
package com.rizlee.data.network.api

import com.rizlee.domain.entities.AphorismBody
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface AphorismApi {

    @GET("api/1.0/")
    fun getRandomAphorism(@Query("method") method: String,
                          @Query("format") format:String,
                          @Query("lang") language: String): Single<AphorismBody>

}
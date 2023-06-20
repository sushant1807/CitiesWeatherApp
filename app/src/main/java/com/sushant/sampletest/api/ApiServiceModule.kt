package com.sushant.sampletest.api

import com.sushant.sampletest.model.CityModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceModule {

    @GET("current.json?")
    suspend fun getCityDetails(
        @Query(value = "key") key: String, @Query(value = "q") cityName: String) : Response<CityModel>

    companion object {
        var retrofitService: ApiServiceModule? = null
        var baseUrl: String =  "https://api.weatherapi.com/v1/"

        fun getInstance() : ApiServiceModule {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(ApiServiceModule::class.java)
            }
            return retrofitService!!
        }
    }
}
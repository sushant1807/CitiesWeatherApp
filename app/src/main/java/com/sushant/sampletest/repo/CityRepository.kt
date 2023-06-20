package com.sushant.sampletest.repo

import com.sushant.sampletest.api.ApiServiceModule
import com.sushant.sampletest.model.CityModel
import com.sushant.sampletest.util.NetworkUtil

class CityRepository constructor(private val retrofitServiceModule: ApiServiceModule) {

    suspend fun getCityDetails(key: String, cityName: String) : NetworkUtil<CityModel> {
        val response = retrofitServiceModule.getCityDetails(key, cityName)
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                NetworkUtil.Success(responseBody)
            } else {
                NetworkUtil.Error(response)
            }
        } else {
            NetworkUtil.Error(response)
        }
    }
}
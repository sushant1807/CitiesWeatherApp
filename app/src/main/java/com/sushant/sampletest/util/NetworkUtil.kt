package com.sushant.sampletest.util

import retrofit2.Response

sealed class NetworkUtil<out T> {
    data class Success<out T>(val data: T): NetworkUtil<T>()
    data class Error<T>(val response: Response<T>): NetworkUtil<T>()
}

fun <T> Response<T>.parseResponse(): NetworkUtil<T> {
    return if (this.isSuccessful && this.body() != null) {
        val responseBody = this.body()
        NetworkUtil.Success(responseBody!!)
    } else {
        NetworkUtil.Error(this)
    }
}
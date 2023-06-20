package com.sushant.sampletest.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sushant.sampletest.model.CityModel
import com.sushant.sampletest.repo.CityRepository
import com.sushant.sampletest.util.NetworkUtil
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CityViewModel constructor(private val cityRepository: CityRepository) : ViewModel() {

    val cityList = MutableLiveData<List<CityModel>>()
    val citiesList: MutableList<CityModel> = mutableListOf()
    //val citiesList = mutableListOf()

    private val cityName = mutableListOf<String>( "Visakhapatnam" , "Hyderabad" , "Pune" , "Delhi" , "Paris")

    fun getCities() {
        Log.d("Thread Outside", Thread.currentThread().name)

        viewModelScope.launch {
            Log.d("Thread Inside", Thread.currentThread().name)
            cityName.forEach {
                when (val response = cityRepository.getCityDetails("520916eb3f46442ca1c12926221402", it)) {
                    is NetworkUtil.Success -> {
                        Log.d("getCities %d", response.toString())
                        //cityList.add
                        //cityList.postValue(listOf(response.data))
                        citiesList.add((response.data))
                    }
                    is NetworkUtil.Error -> {
                        if (response.response.code() == 401) {
                            //Display the error
                        }
                    }
                }
            }
            Log.d("Cities list", citiesList.count().toString())
            cityList.postValue(citiesList)
        }
    }
}
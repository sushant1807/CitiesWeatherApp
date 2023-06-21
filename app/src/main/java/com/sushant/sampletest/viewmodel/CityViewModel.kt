package com.sushant.sampletest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sushant.sampletest.model.CityModel
import com.sushant.sampletest.repo.CityRepository
import com.sushant.sampletest.util.NetworkUtil
import kotlinx.coroutines.launch

class CityViewModel constructor(private val cityRepository: CityRepository) : ViewModel() {

    val cityList = MutableLiveData<List<CityModel>>()
    private val citiesList: MutableList<CityModel> = mutableListOf()

    private val cityName = mutableListOf<String>( "Visakhapatnam" , "Hyderabad" , "Pune" , "Delhi" , "Paris")

    fun getCities() {
        viewModelScope.launch {
            cityName.forEach {
                when (val response = cityRepository.getCityDetails("520916eb3f46442ca1c12926221402", it)) {
                    is NetworkUtil.Success -> {
                        citiesList.add((response.data))
                    }
                    is NetworkUtil.Error -> {
                        if (response.response.code() == 401) {
                            //Display the error
                        }
                    }
                }
            }
            cityList.postValue(citiesList)
        }
    }
}
package com.sushant.sampletest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sushant.sampletest.repo.CityRepository
import com.sushant.sampletest.viewmodel.CityViewModel

class CityViewModelFactory constructor(private val cityRepo: CityRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CityViewModel::class.java)) {
            CityViewModel(this.cityRepo) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
package com.sushant.sampletest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sushant.sampletest.CityViewModelFactory
import com.sushant.sampletest.adapter.CityListAdapter
import com.sushant.sampletest.api.ApiServiceModule
import com.sushant.sampletest.databinding.MainActivityBinding
import com.sushant.sampletest.repo.CityRepository
import com.sushant.sampletest.viewmodel.CityViewModel

class MainActivity: AppCompatActivity() {

    lateinit var viewModel: CityViewModel
    private val adapter = CityListAdapter()
    lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofitService = ApiServiceModule.getInstance()
        val mainRepository = CityRepository(retrofitService)
        binding.recyclerview.adapter = adapter

        viewModel = ViewModelProvider(this, CityViewModelFactory(mainRepository)).get(CityViewModel::class.java)

        viewModel.cityList.observe(this) {
            adapter.setCities(it)
        }

        viewModel.getCities()
    }
}
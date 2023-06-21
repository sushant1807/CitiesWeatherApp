package com.sushant.sampletest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sushant.sampletest.databinding.CityItemViewBinding
import com.sushant.sampletest.model.CityModel

class CityListAdapter: RecyclerView.Adapter<CityViewHolder>() {

    var cityList = mutableListOf<CityModel>()

    fun setCities(cityModelList: List<CityModel>) {
        this.cityList = cityModelList.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CityItemViewBinding.inflate(inflater, parent, false)
        return CityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cityList[position]
        val item = city.location.name + " - " + city.current.temp_f + "°F" + " - " + city.current.wind_kph + " kmph" +" - " + city.current.condition.text
        holder.binding.name.text = item
    }
}

class CityViewHolder(val binding: CityItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

}
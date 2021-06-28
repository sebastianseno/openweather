package com.dodolife.weather.ui.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dodolife.weather.database.entity.WeatherDb
import com.dodolife.weather.databinding.ItemTemperatureBinding
import com.dodolife.weather.extensions.toDateFormat
import com.dodolife.weather.extensions.toCelciusFormat

class WeatherAdapter(
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    var items = mutableListOf<WeatherDb>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemTemperatureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
            return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(private val containerView: ItemTemperatureBinding) :
        RecyclerView.ViewHolder(containerView.root) {

        init {
            containerView.root.setOnClickListener {
                onClick(items[adapterPosition].id)
            }
        }
        fun bind(data: WeatherDb) {
            containerView.apply {
                icon.load("https://openweathermap.org/img/wn/${data.icon}.png")
                temp.text = data.temp.toCelciusFormat()
                date.text = data.date.toDateFormat()
                city.text = data.name
            }
        }
    }
}

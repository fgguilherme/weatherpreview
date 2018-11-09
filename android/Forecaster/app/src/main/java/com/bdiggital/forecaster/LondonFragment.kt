package com.bdiggital.forecaster

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bdiggital.forecaster.model.Forecast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_london.*

class LondonFragment : Fragment(),OpenWeatherCallback {
    override fun onUpdate(forecast: Forecast?) {
        if(!isResumed){
            return
        }
        var icon = forecast?.weather?.get(0)?.icon

        if(icon == null){
            icon = "50d"
        }

        val iconUrl = "http://openweathermap.org/img/w/$icon.png"

        Glide.with(context).load(iconUrl).into(image_london)

        val humidity = forecast?.main?.humidity?.toString()+"%"

        text_humidity_london.text = humidity

        val temperature = forecast?.main?.temp?.toString()+"˚C"

        text_temp_london.text = temperature

        val max = forecast?.main?.tempMax?.toString()+"˚C"

        text_max_temp_london.text = max

        val min = forecast?.main?.tempMin?.toString()+"˚C"

        text_min_temp_london.text = min

        var city = forecast?.name?.toString()

        if(city == null){
            city = "--"
        }
        text_city_london.text = city
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        OpenWeatherController.getLondon(this)
        return inflater.inflate(R.layout.fragment_london, container, false)
    }

}

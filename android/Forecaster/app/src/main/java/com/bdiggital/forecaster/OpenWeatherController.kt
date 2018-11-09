package com.bdiggital.forecaster

import android.util.Log
import com.bdiggital.forecaster.model.Forecast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object OpenWeatherController {
    private lateinit var owConn:OpenWeatherConnector
    private lateinit var mRetrofit:Retrofit

    fun init(){
        mRetrofit = Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        owConn = mRetrofit.create(OpenWeatherConnector::class.java)
    }

    fun getLatLon(lat: String, lon: String, callback: OpenWeatherCallback) {
        owConn.localForecast(lat,lon).enqueue(object : Callback<Forecast> {
            override fun onResponse(call: Call<Forecast>, response: Response<Forecast>) {
                callback.onUpdate(response.body())
            }

            override fun onFailure(call: Call<Forecast>, t: Throwable) {
                Log.d("myResponse:", "MSG" + t.toString())
            }
        })
    }

    fun getLondon(callback: OpenWeatherCallback) {
        val call = owConn.londonForecast
        call.enqueue( object : Callback<Forecast> {
            override fun onResponse(call: Call<Forecast>, response: Response<Forecast>) {
                callback.onUpdate(response.body())
            }

            override fun onFailure(call: Call<Forecast>, t: Throwable) {
                Log.d("myResponse:", "MSG" + t.toString())
            }
        })
    }
}
package com.bdiggital.forecaster

import com.bdiggital.forecaster.model.Forecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherConnector {
    @get:GET("weather?q=London,gb&appid=18d0db1f9b28b65a886f11821a2ed68c&units=metric")
    val londonForecast: Call<Forecast>
    @GET("weather?appid=18d0db1f9b28b65a886f11821a2ed68c&units=metric")
    fun localForecast(@Query("lat") place: String, @Query("lon") appId: String):Call<Forecast>
}
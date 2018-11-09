package com.bdiggital.forecaster

import com.bdiggital.forecaster.model.Forecast

interface OpenWeatherCallback {
    fun onUpdate(forecast: Forecast?)
}
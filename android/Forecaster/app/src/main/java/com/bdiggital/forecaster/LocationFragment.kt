package com.bdiggital.forecaster

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bdiggital.forecaster.model.Forecast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_location.*


class LocationFragment : Fragment(), OpenWeatherCallback {

    override fun onUpdate(forecast: Forecast?) {
        if(!isResumed){
            return
        }
        var icon = forecast?.weather?.get(0)?.icon

        if(icon == null){
            icon = "50d"
        }

        val iconUrl = "http://openweathermap.org/img/w/$icon.png"

        Glide.with(context).load(iconUrl).into(image_location)

        val humidity = forecast?.main?.humidity?.toString()+"%"

        text_humidity_location.text = humidity

        val temperature = forecast?.main?.temp?.toString()+"˚C"

        text_temp_location.text = temperature

        val max = forecast?.main?.tempMax?.toString()+"˚C"

        text_max_temp_location.text = max

        val min = forecast?.main?.tempMin?.toString()+"˚C"

        text_min_temp_location.text = min

        var city = forecast?.name?.toString()

        if(city == null){
            city = "--"
        }
        text_city.text = city

    }

    private var locationManager : LocationManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val permissionCoarse = ContextCompat.checkSelfPermission(context!!,
            Manifest.permission.ACCESS_COARSE_LOCATION)

        val permissionFine = ContextCompat.checkSelfPermission(context!!,
            Manifest.permission.ACCESS_FINE_LOCATION)

        if (permissionCoarse == PackageManager.PERMISSION_GRANTED ||
            permissionFine == PackageManager.PERMISSION_GRANTED) {
            startLocationTracker()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        startLocationTracker()
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    private fun startLocationTracker(){
        locationManager = activity?.getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager?

        val locationListener: LocationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                //fullscreen_content.text = ""+location.longitude.toString()+":"+location.latitude.toString()//"${location.longitude}:${location.latitude}"
                OpenWeatherController.getLatLon(location.latitude.toString(),location.longitude.toString(),this@LocationFragment)
            }
            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }
        try {
            // Request location updates
            locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)
        } catch(ex: SecurityException) {
            Log.e("FORECASTER_LOCATION", "Security Exception, no location available")
        }
    }

}

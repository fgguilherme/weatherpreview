package com.bdiggital.forecaster

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.facebook.AccessToken
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)


        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired
        if (isLoggedIn) {
            Toast.makeText(this,R.string.login_ok, Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this,R.string.login_error,Toast.LENGTH_LONG).show()
        }

        val permissionCoarse = ContextCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_COARSE_LOCATION)

        val permissionFine = ContextCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_FINE_LOCATION)

        if (permissionCoarse != PackageManager.PERMISSION_GRANTED ||
            permissionFine != PackageManager.PERMISSION_GRANTED) {
            Log.e("FORECASTER_PERMISSION", "Permission to record denied")
            ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),1)
        }
        OpenWeatherController.init()
        val fm = this.supportFragmentManager
        val adapter = Adapter(fm)
        adapter.addFragment(LocationFragment(),getString(R.string.title_location))
        adapter.addFragment(LondonFragment(),getString(R.string.title_london))
        adapter.addFragment(FacebookLoginFragment(),getString(R.string.title_facebook))
        home_viewpager.adapter = adapter
        home_tabs.setupWithViewPager(home_viewpager)

    }

    internal class Adapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }
    }

}

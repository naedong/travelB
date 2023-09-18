package kr.com.map.util

import android.content.Context
import android.location.LocationManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-09-05
 * Time: 오전 11:19
 */
@Singleton
class LocationHelper @Inject constructor(
    @ApplicationContext private val context : Context
){

    fun isConnected() : Boolean {
        val location =
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return location.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }
}
package fr.hetic.h3p2021

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_geoloc.*
import kotlinx.android.synthetic.main.activity_geoloc.view.*

class GeolocActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_geoloc)

        val isGranted = isPermissionGranted()
        if (isGranted) {
            startGeoloc()
        } else {
            askPermissions()
        }
    }

    private fun isPermissionGranted(): Boolean {
        val permissionFineStatus = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        val permissionCoarseStatus = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)

        return permissionFineStatus == PackageManager.PERMISSION_GRANTED
                && permissionCoarseStatus == PackageManager.PERMISSION_GRANTED
    }

    @SuppressLint("MissingPermission")
    private fun startGeoloc() {

        val fuseLocationClient = LocationServices.getFusedLocationProviderClient(this)

        fuseLocationClient.lastLocation.addOnSuccessListener { location ->
            latitude.text = "Latitude ${location.latitude}"
            longitude.text = "Longitude ${location.longitude}"
            speed.text = "Speed ${location.speed}"
            accuracy.text = "Accuracy ${location.accuracy}"
            time.text = "Time ${location.time}"
            altitude.text = "Altitude ${location.altitude}"
        }


        val locationRequest = LocationRequest.create().apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        fuseLocationClient.requestLocationUpdates(
            locationRequest,
            object : LocationCallback() {
                override fun onLocationResult(locationRequest: LocationResult?) {
                    locationRequest ?: return

                    val location = locationRequest.lastLocation

                    latitude.text = "Latitude ${location.latitude}"
                    longitude.text = "Longitude ${location.longitude}"
                    speed.text = "Speed ${location.speed}"
                    accuracy.text = "Accuracy ${location.accuracy}"
                    time.text = "Time ${location.time}"
                    altitude.text = "Altitude ${location.altitude}"
                }
            },
            Looper.getMainLooper()
        )









    }

    private fun askPermissions() {
        val permissionsList = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        requestPermissions(permissionsList, 789)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 789) {
            val isGranted = isPermissionGranted()
            if (isGranted) {
                startGeoloc()
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}

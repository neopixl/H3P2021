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

class GeolocActivity : AppCompatActivity() {

    var locationCallback = object: LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult ?: return

            val location = locationResult.lastLocation
            latitude.text = "Latitude : ${location.latitude}"
            longitude.text = "Longitude : ${location.longitude}"
            date.text = "Time : ${location.time}"
            speed.text = "Speed : ${location.speed}"
            altitude.text = "Altitude Bro : ${location.altitude}"
            provider.text = "Provider : ${location.provider}"
            accuracy.text = "Accuracy : ${location.accuracy}"


        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_geoloc)

        val isGranted = isPermissionGranted()

        if (!isGranted) {
           askForPermissions()
        } else {
            startGeoloc()
        }
    }

    override fun onStop() {
        val fusedLocation = LocationServices.getFusedLocationProviderClient(this)
        fusedLocation.removeLocationUpdates(locationCallback)

        super.onStop()
    }

    fun isPermissionGranted() : Boolean {
        val permissionStatusFine = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        val permissionStatusCoarse = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)

        return permissionStatusFine == PackageManager.PERMISSION_GRANTED
                && permissionStatusCoarse == PackageManager.PERMISSION_GRANTED

    }

    fun askForPermissions() {
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        requestPermissions(permissions, 660)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 660) {
            val isGranted = isPermissionGranted()
            if (isGranted) {
                startGeoloc()
            }
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    @SuppressLint("MissingPermission")
    fun startGeoloc() {

        val fusedLocation = LocationServices.getFusedLocationProviderClient(this)

        fusedLocation.lastLocation.addOnSuccessListener {location ->
            latitude.text = "Latitude : ${location.latitude}"
            longitude.text = "Longitude : ${location.longitude}"
            date.text = "Time : ${location.time}"
            speed.text = "Speed : ${location.speed}"
            altitude.text = "Altidute : ${location.altitude}"
            provider.text = "Provider : ${location.provider}"
            accuracy.text = "Accuracy : ${location.accuracy}"
        }



        val locationRequest = LocationRequest.create()?.apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        fusedLocation.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())

    }

}

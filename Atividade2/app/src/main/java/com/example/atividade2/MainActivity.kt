package com.example.atividade2

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices

private lateinit var sensorManager : SensorManager
private lateinit var light : Sensor
private lateinit var temperature : Sensor

private lateinit var fusedLocation: FusedLocationProviderClient



class MainActivity : AppCompatActivity(), SensorEventListener {




    private lateinit var lightView: TextView
    private lateinit var temperatureView: TextView
    private lateinit var latitudeView: TextView
    private lateinit var longitudeView: TextView
    private lateinit var altitudeView: TextView
    private lateinit var gpsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fusedLocation = LocationServices.getFusedLocationProviderClient(this)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        temperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)

        lightView = findViewById<TextView>(R.id.light)
        temperatureView = findViewById<TextView>(R.id.temperature)
        latitudeView = findViewById<TextView>(R.id.latitude)
        longitudeView = findViewById<TextView>(R.id.longitude)
        altitudeView = findViewById<TextView>(R.id.altitude)
        gpsButton = findViewById<Button>(R.id.gps_button)
        gpsButton.setOnClickListener {
            gps()
        }
    }

    @SuppressLint("MissingPermission")
    fun gps() {
        gpsButton.setOnClickListener{
            fusedLocation.lastLocation
                .addOnSuccessListener{
                    latitudeView.text = it.altitude.toString()
                    longitudeView.text = it.longitude.toString()
                    altitudeView.text = it.latitude.toString()
                }
        }

    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_FASTEST)
        sensorManager.registerListener(this, temperature, SensorManager.SENSOR_DELAY_FASTEST)
    }


    override fun onSensorChanged(event: SensorEvent) {
        if(event.sensor.type == Sensor.TYPE_LIGHT)
        {
            lightView.text = event.values[0].toString()
        }

        if(event.sensor.type == Sensor.TYPE_AMBIENT_TEMPERATURE)
        {
           temperatureView.text = event.values[0].toString()
        }

    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        return
    }
}
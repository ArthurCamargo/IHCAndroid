package com.example.atividade13

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.atividade2.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

private lateinit var sensorManager : SensorManager
private lateinit var light : Sensor
private lateinit var temperature : Sensor

private lateinit var fusedLocation: FusedLocationProviderClient


class MainActivity : AppCompatActivity(), SensorEventListener {



    private val light_view = findViewById<TextView>(R.id.light)
    private val temperature_view = findViewById<TextView>(R.id.temperature)
    private val latitude_view = findViewById<TextView>(R.id.latitude)
    private val longitude_view = findViewById<TextView>(R.id.longitude)
    private val altitude_view = findViewById<TextView>(R.id.altitude)
    private val gps_button = findViewById<Button>(R.id.gps_button)


    private var fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        temperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @SuppressLint("MissingPermission")
    fun gps() {
        gps_button.setOnClickListener{
            fusedLocationClient.lastLocation
                .addOnSuccessListener{
                    latitude_view.text = it.altitude.toString()
                    longitude_view.text = it.longitude.toString()
                    altitude_view.text = it.latitude.toString()
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
            light_view.text = event.values[0].toString()
        }

        if(event.sensor.type == Sensor.TYPE_AMBIENT_TEMPERATURE)
        {
            temperature_view.text = event.values[0].toString()
        }

    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
}
package com.example.atividade13

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlin.math.abs

private lateinit var sensorManager: SensorManager
private lateinit var accelerometer: Sensor


private lateinit var axisX: TextView
private lateinit var axisY: TextView
private lateinit var axisZ: TextView


class MainActivity : AppCompatActivity(), SensorEventListener{



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        axisX = findViewById<TextView>(R.id.axisx)
        axisY = findViewById<TextView>(R.id.axisy)
        axisZ = findViewById<TextView>(R.id.axisz)

        setUpSensor()

    }

    private fun setUpSensor() {
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_FASTEST)
    }

    override fun onSensorChanged(event: SensorEvent) {

        val intent = Intent(this, MainActivity2::class.java).apply {}

        if(event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            var sensorX = event.values[0]
            var sensorY = event.values[1]
            var sensorZ = event.values[2]

            axisX.text = sensorX.toString()
            axisY.text = sensorY.toString()
            axisZ.text = sensorZ.toString()

            if((sensorX + sensorY + sensorZ) > 20) {
                startActivity(intent)
            }
        }

    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        return
    }
}

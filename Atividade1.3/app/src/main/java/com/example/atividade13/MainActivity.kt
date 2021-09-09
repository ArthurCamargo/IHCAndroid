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

class MainActivity : AppCompatActivity(), SensorEventListener {
    private var coordinate = findViewById<TextView>(R.id.sucesso)

    private val axisX = findViewById<TextView>(R.id.axisx)
    private val axisY = findViewById<TextView>(R.id.axisy)
    private val axisZ = findViewById<TextView>(R.id.axisz)



    override fun onCreate(savedInstanceState: Bundle?) {
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

            var lastX = sensorX
            var lastY = sensorY
            var lastZ = sensorZ

            if(abs(sensorX - lastX) > abs(sensorY - lastY) && abs(sensorX - lastX) > abs(sensorZ - lastZ)) {
                startActivity(intent)
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
}
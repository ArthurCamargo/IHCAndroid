package com.example.atividade1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val number1 = findViewById<EditText>(R.id.number1)
        val number2 = findViewById<EditText>(R.id.number2)

        val sum = findViewById<Button>(R.id.sum)

        val value = findViewById<TextView>(R.id.value)

        sum.setOnClickListener {
            var field1 = number1.text.toString()
            var field2 = number2.text.toString()
            println(field1)
            println(field2)
            if (field1 == "")
                field1 = "0"
            if (field2 == "")
                field2 = "0"
            value.text = (field1.toInt() + field2.toInt()).toString()
        }
    }
}
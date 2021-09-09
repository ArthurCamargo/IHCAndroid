package com.example.atividade2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.view.View
import android.content.Intent
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.send)

        button.setOnClickListener {
            sendMessage(it)
        }

    }

    fun sendMessage(view: View)
    {
        val editText = findViewById<EditText>(R.id.message)
        val message = editText.text.toString()
        val intent = Intent(this, MainActivity2::class.java).apply {
            putExtra("data", message)
        }
        startActivity(intent)
    }
}
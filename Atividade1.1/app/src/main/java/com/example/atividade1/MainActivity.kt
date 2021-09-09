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

        val numero1 = findViewById<EditText>(R.id.numero1);
        val numero2 = findViewById<EditText>(R.id.numero2);

        val botao = findViewById<Button>(R.id.botao);

        val soma = findViewById<TextView>(R.id.soma);

        botao.setOnClickListener {
            val sum = numero1.toString().toInt() + numero2.toString().toInt();
            soma.text = sum.toString();
        }
    }
}
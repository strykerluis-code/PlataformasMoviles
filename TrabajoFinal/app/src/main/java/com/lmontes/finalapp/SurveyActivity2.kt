package com.lmontes.finalapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SurveyActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_survey2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val boton1 = findViewById<Button>(R.id.boton1)
        val boton2 = findViewById<Button>(R.id.boton2)
        val boton3 = findViewById<Button>(R.id.boton3)
        val boton4 = findViewById<Button>(R.id.boton4)

        // Set click listeners for buttons
        setButtonListener(boton1, 4)
        setButtonListener(boton1, 3)
        setButtonListener(boton1, 2)
        setButtonListener(boton4, 1)
    }
    // Function to handle BUTTON selection
    private fun setButtonListener(button: Button, option: Int) {
        button.setOnClickListener() {
            // Nos manda a la siguiente pregunta de la encuesta
            val intent = Intent(this, SurveyActivity3::class.java)
            startActivity(intent)
            //Envia la respuesta a la base de datos
        }
    }

}
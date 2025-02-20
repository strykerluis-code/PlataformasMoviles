package com.lmontes.finalapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SurveyActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_survey3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val botonA = findViewById<Button>(R.id.botonA)
        val botonB = findViewById<Button>(R.id.botonB)
        val botonC = findViewById<Button>(R.id.botonC)
        val botonD = findViewById<Button>(R.id.botonD)

        // Set click listeners for buttons
        setButtonListener(botonA, 4)
        setButtonListener(botonB, 3)
        setButtonListener(botonC, 2)
        setButtonListener(botonD, 1)
    }
    // Function to handle BUTTON selection
    private fun setButtonListener(button: Button, option: Int) {
        button.setOnClickListener() {
            // Nos manda a la siguiente pregunta de la encuesta
            val intent = Intent(this, SurveyEndActivity::class.java)
            startActivity(intent)
            //Envia la respuesta a la base de datos
        }
    }

}
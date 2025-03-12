package com.lmontes.finalapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val startSurveyButton = findViewById<Button>(R.id.btnRegistroEncuesta)
        val startReservaButton = findViewById<Button>(R.id.btnRegistroReserva)


        startSurveyButton.setOnClickListener() {
            val intent = Intent(this, SurveyActivity1::class.java)
            startActivity(intent)
        }

        startReservaButton.setOnClickListener() {
            val intent = Intent(this, ListaHabitacionesActivity::class.java)
            startActivity(intent)
        }

    }
}
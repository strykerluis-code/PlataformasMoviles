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
        val startButton = findViewById<Button>(R.id.btnRegistro)



        startButton.setOnClickListener() {
            val intent = Intent(this, SurveyActivity1::class.java)
            startActivity(intent)
        }

        startButton.setOnClickListener() {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }




        startButton.setOnClickListener() {
            val intent = Intent(this, ListaHabitacionesActivity::class.java)
            startActivity(intent)
        }

    }
}
package com.lmontes.finalapp


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SurveyEndActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_survey_end)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val botonA = findViewById<Button>(R.id.promo_button)
        setButtonListener(botonA)
    }
    private fun setButtonListener(button: Button) {
        button.setOnClickListener() {
            // Nos manda a promociones
            val intent = Intent(this, GenerarDescuentoActivity::class.java)
            startActivity(intent)
            //Envia la respuesta a la base de datos
        }
    }
}
package com.lmontes.finalapp

import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SurveyActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_survey1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val checkBoxA = findViewById<CheckBox>(R.id.checkBoxA)
        val checkBoxB = findViewById<CheckBox>(R.id.checkBoxB)
        val checkBoxC = findViewById<CheckBox>(R.id.checkBoxC)
        val checkBoxD = findViewById<CheckBox>(R.id.checkBoxD)

        // Set click listeners for checkboxes
        setCheckboxListener(checkBoxA, 1)
        setCheckboxListener(checkBoxA, 2)
        setCheckboxListener(checkBoxA, 3)
        setCheckboxListener(checkBoxA, 4)

    }
    // Function to handle checkbox selection
    private fun setCheckboxListener(checkBox: CheckBox, option: Int) {
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Nos manda a la siguiente pregunta de la encuesta
                val intent = Intent(this, SurveyActivity2::class.java)
                startActivity(intent)
            }
        }
    }

}
package com.lmontes.finalapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.lmontes.finalapp.R


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

        var ambientesList = getCheckedCheckboxesContent(checkBoxA, checkBoxB, checkBoxC, checkBoxD)

        val boton1 = findViewById<Button>(R.id.next_button1)
        setButtonListener(boton1, ambientesList)

    }

    // get checkbox value and add it to the list
    fun getCheckedCheckboxesContent(checkBoxA: CheckBox,checkBoxB: CheckBox,checkBoxC: CheckBox,checkBoxD: CheckBox): List<String> {
        val ambienteList = mutableListOf<String>()

        checkBoxA.setOnClickListener {
            if (checkBoxA.isChecked) {
                var textoCheckbox = checkBoxA.text.toString()
                ambienteList.add(textoCheckbox)
            }
        }
        checkBoxB.setOnClickListener {
            if (checkBoxB.isChecked) {
                var textoCheckbox = checkBoxB.text.toString()
                ambienteList.add(textoCheckbox)
            }
        }
        checkBoxC.setOnClickListener {
            if (checkBoxC.isChecked) {
                var textoCheckbox = checkBoxC.text.toString()
                ambienteList.add(textoCheckbox)
            }
        }
        checkBoxD.setOnClickListener {
            if (checkBoxD.isChecked) {
                var textoCheckbox = checkBoxD.text.toString()
                ambienteList.add(textoCheckbox)
            }
        }
        return ambienteList
    }


    private fun setButtonListener(button: Button, ambienteList: List<String>) {
        button.setOnClickListener() {
            val intent = Intent(this, SurveyActivity2::class.java)

            //ambienteList a string
            val ambientes = ambienteList.joinToString(";")
            intent.putExtra("key", ambientes)
            Log.i("=====>", ambientes)
            startActivity(intent)
        }
    }

}
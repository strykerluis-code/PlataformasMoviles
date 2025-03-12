package com.lmontes.finalapp


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import android.content.Intent
import android.widget.Button

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

        //obtener lista de la pregunta anterior
        val respuestas = intent.getStringExtra("key").toString()

        // 1. Find buttons inside CardViews
        val botonA = findViewById<Button>(R.id.botonA)
        val botonB = findViewById<Button>(R.id.botonB)
        val botonC = findViewById<Button>(R.id.botonC)
        val botonD = findViewById<Button>(R.id.botonD)

        // Set click listeners for buttons
        setButtonListener(botonA, 4, respuestas)
        setButtonListener(botonB, 3, respuestas)
        setButtonListener(botonC, 2, respuestas)
        setButtonListener(botonD, 1, respuestas)
    }
    // Function to handle BUTTON selection
    private fun setButtonListener(button: Button, hospitalidad: Int,  respuestas: String) {
        button.setOnClickListener() {

            val ambientes = respuestas.substringBefore(",")
            val estadia = respuestas.substringAfter(",").toInt()

            val url = "https://dfaoz0y8xk.execute-api.us-east-1.amazonaws.com/v1/encuesta"

            val jsonObject = JSONObject()
            try {
                jsonObject.put("ambientes", ambientes)
                jsonObject.put("estadia", estadia)
                jsonObject.put("hospitalidad", hospitalidad)
            } catch (e: JSONException) {
                Log.i("=====>", e.message.toString())
            }

            val jsonObjReq = object : JsonObjectRequest(
                Method.POST, url, jsonObject,
                Response.Listener {
                    Log.i("=====>", "Encuesta insertada con exito")
                },
                Response.ErrorListener {  error: VolleyError ->
                    Log.i("=====>", error.message.toString())
                }
            ) {}

            val requestQueue = Volley.newRequestQueue(this)
            requestQueue.add(jsonObjReq)

            // Nos manda a la siguiente pregunta de la encuesta
            val intent = Intent(this, SurveyEndActivity::class.java)
            startActivity(intent)
        }
    }

}

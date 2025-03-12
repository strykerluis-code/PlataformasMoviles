package com.lmontes.finalapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
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

class GenerarDescuentoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_generar_descuento)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun registrar(view: View) {

        val txtNombre = findViewById<EditText>(R.id.txtNombre)
        val txtApellido = findViewById<EditText>(R.id.txtApellido)

        val url = "https://dfaoz0y8xk.execute-api.us-east-1.amazonaws.com/v1/descuento"

        val jsonObject = JSONObject()
        try {
            jsonObject.put("nombre", txtNombre.text.toString())
            jsonObject.put("apellido", txtApellido.text.toString())

        } catch (e: JSONException) {
            Log.i("=====>", e.message.toString())
        }

        val jsonObjReq = object : JsonObjectRequest(
            Method.POST, url, jsonObject,
            Response.Listener {
                Log.i("=====>", "Descuento generado con exito")
            },
            Response.ErrorListener {  error: VolleyError ->
                Log.i("=====>", error.message.toString())
            }
        ) {}

        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(jsonObjReq)

        //Ir a la sgte actividad
        val intent = Intent(this, CuponActivity::class.java)
        startActivity(intent)
    }

}
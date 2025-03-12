package com.lmontes.finalapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.common.api.Response
import com.google.androidgamesdk.gametextinput.Listener
import org.json.JSONException
import org.json.JSONObject
import java.lang.reflect.Method

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val spinner = findViewById<Spinner>(R.id.spinner)
        val lista = arrayOf("Nombre", "Apellido", "Sexo", "Casado", "Cantidad de hijos", "Cantidad de visitas", "Celular", "Email", "Dirección", "Región", "País")
        val adaptador = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista)
        spinner.adapter = adaptador


    }

    fun registrar(view: View) {

        var txtCategoria = 1
        when (spinner.selectedItem.toString()) {
            "Nombre" -> txtCategoria = 1
            "Apellido" -> txtCategoria = 2
            "Sexo" -> txtCategoria = 3
            "Casado" -> txtCategoria = 4
            "Cantidad de hijos" -> txtCategoria = 5
            "Cantidad de visitas" -> txtCategoria = 6
            "Celular" -> txtCategoria = 7
            "Email" -> txtCategoria = 8
            "Dirección" -> txtCategoria = 9
            "Región" -> txtCategoria = 10
            "País" -> txtCategoria = 11
        }

        val txtNombre = findViewById<EditText>(R.id.txtNombre)
        val txtApellido = findViewById<EditText>(R.id.txtPrecio)
        val txtSexo = findViewById<EditText>(R.id.txtSexo)
        val txtCasado = findViewById<EditText>(R.id.txtCasado)
        val txtCantidadHijos = findViewById<EditText>(R.id.txtCantidadHijos)
        val txtCantiVisitas = findViewById<EditText>(R.id.txtCantidadVisitas)
        val txtCelular = findViewById<EditText>(R.id.txtCelular)
        val txtEmail = findViewById<EditText>(R.id.txtEmail)
        val txtDireccion = findViewById<EditText>(R.id.txtDireccion)
        val txtRegion = findViewById<EditText>(R.id.txtRegion)
        val txtPais = findViewById<EditText>(R.id.txtPais)

        val url = "https://dfaoz0y8xk.execute-api.us-east-1.amazonaws.com/v1/cliente"
        val jsonObject = JSONObject()
        try {
            jsonObject.put("nombre", txtNombre.text.toString())
            jsonObject.put("apellido", txtApellido.text.toString().toFloat())
            jsonObject.put("sexo", txtSexo.text.toString())
            jsonObject.put("casado", txtCasado)
            jsonObject.put("cantidad de visitas", txtCantiVisitas)
            jsonObject.put("cantidad de hijos", txtCantidadHijos)
            jsonObject.put("celular", txtCelular)
            jsonObject.put("email", txtEmail)
            jsonObject.put("dirección", txtDireccion)
            jsonObject.put("region", txtRegion)
            jsonObject.put("pais", txtPais)

        } catch (e: JSONException) {
            Log.i("=====>", e.message.toString())
        }

        val jsonObjReq = object : JsonObjectRequest(
            Method.POST, url, jsonObject,
            Response.Listener {
                Log.i("===>", "Producto creado con exito")
            },
            Response.ErrorListener {  error: VolleyError ->
                Log.i("===>", error.message.toString())
            }
        ) {}

        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(jsonObjReq)
    }
}
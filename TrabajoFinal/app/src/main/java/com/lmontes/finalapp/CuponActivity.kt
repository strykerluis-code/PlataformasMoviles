package com.lmontes.finalapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request.Method
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException


class CuponActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cupon)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        buscar(findViewById(R.id.main))
    }
    //GET dscuento generado
    fun buscar(view: View) {

        val url = "https://dfaoz0y8xk.execute-api.us-east-1.amazonaws.com/v1/descuento2?"
        //Thread.sleep(10_000)
        val stringRequest = JsonObjectRequest(Method.GET, url, null,
            {
                    response ->
                try {
                    val jsonArray = response.getJSONArray("data")
                    Log.i("=====>", jsonArray.toString())

                    val items = mutableListOf<String>()
                    var printDescripcion="";
                    var printDescuento= "";
                    var printCodDescuento="";

                    for (i in 0..jsonArray.length()) {
                        val registro = jsonArray.getJSONObject(i)
                        items.add(registro.toString())
                    }

                    //print datos del descuento en cupon
                    var txtDescripcion = findViewById<TextView>(R.id.txtDescripcion)
                    printDescripcion = items[1]
                    Log.i("aaaaaa",printDescripcion)
                    txtDescripcion.text = printDescripcion

                } catch (e: JSONException) {
                    Log.i("=====>", "Error:")
                    Log.i("=====>", e.message.toString())
                }
            },
            {
                    error ->
                Log.i("=====>", error.toString())
            }
        )

        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }

}
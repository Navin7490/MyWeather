package com.example.myweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.textclassifier.ConversationActions
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var edittextcity:EditText
    lateinit var buttonget:Button
    lateinit var textviewtemprature:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edittextcity=findViewById(R.id.edittextcity)
        buttonget=findViewById(R.id.buttongetweather)
        textviewtemprature=findViewById(R.id.textviewtemprature)

        buttonget.setOnClickListener {
            getdata()
        }
    }


    fun getdata() {
        var apikey="b7b129d04f0e80ed6654114eb97d0be7"
        var url="https://api.openweathermap.org/data/2.5/weather?q=ahmedabad&appid=b7b129d04f0e80ed6654114eb97d0be7"

        var request:JsonObjectRequest= JsonObjectRequest(Request.Method.GET,url,
        null,Response.Listener {
            response ->
            var mains:JSONObject=response.getJSONObject("main")
            var tempreture=mains.getString("temp")
                textviewtemprature.text = tempreture


        },Response.ErrorListener {

        })


        var queue:RequestQueue=Volley.newRequestQueue(this)
        queue.add(request)

    }


}




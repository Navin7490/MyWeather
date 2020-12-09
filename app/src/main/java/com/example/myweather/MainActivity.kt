package com.example.myweather

import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.textclassifier.ConversationActions
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.datatransport.runtime.backends.BackendResponse
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import org.json.JSONObject
import java.text.Format
import java.util.*
import javax.net.ssl.SSLEngineResult

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

        // Initialize place

       // Places.initialize(application,"AIzaSyCYd9DNtP8fAnic_H5XwgCef7dmqj_7vB0")
        Places.initialize(application,"AIzaSyDXwsCguL_6V-3APwq65-ZhupEaHuHEbU8")


        edittextcity.isFocusable=false

        buttonget.setOnClickListener {
            getdata()
        }

        edittextcity.setOnClickListener {

            // initilize place fielde
        var fieldList: List<Place.Field> =Arrays.asList(Place.Field.ADDRESS,
        Place.Field.LAT_LNG,Place.Field.NAME)

            // create Intent
            var intent:Intent=Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN,
            fieldList).build(this)

            // start activity result
            startActivityForResult(intent,100)


        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==100 && resultCode== Activity.RESULT_OK){
            // when success
            //Initialize place
            var place:Place=Autocomplete.getPlaceFromIntent(data!!)
            // set address on edittext
            edittextcity.setText(place!!.name)

            // setlocality name
          //  textviewtemprature.setText(String.format("Locality name :%s",place.name))

            // set latitude & longitude

          //  textviewtemprature.setText(String.toString())
        } else if (resultCode==AutocompleteActivity.RESULT_ERROR) {

            // when error
            // Initialize Status

            var status: Status = Autocomplete.getStatusFromIntent(data!!)

            // display toast
            Toast.makeText(this,status.statusMessage,Toast.LENGTH_SHORT).show()



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




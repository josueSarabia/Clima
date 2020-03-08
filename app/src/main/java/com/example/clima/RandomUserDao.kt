package com.example.clima

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class RandomUserDao private constructor( var context: Context){

    private val users = MutableLiveData<List<RandomUser>>()
    private val userList = mutableListOf<RandomUser>()
    private var queue : RequestQueue

    init{
        queue = VolleySingleton.getInstance(context).requestQueue
    }

    companion object{
        @Volatile
        private  var INSTANCE: RandomUserDao? = null
        fun getInstance(context: Context) =
            INSTANCE ?: RandomUserDao(context).also{
                INSTANCE = it
            }
    }

    fun addUser(){
        VolleySingleton.getInstance(context).addToRequestQueue(getJsonObjectRequest())
    }

    fun getUsers() = users

    fun getJsonObjectRequest() : JsonObjectRequest{

        val url = "https://api.openweathermap.org/data/2.5/group?id=3689147,688689,3687238,3687925,3674962,3668605,672778,3670745,3688465,3666304&units=metric&name&appid=96e265d7571b193021470794bc491435"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                parseObjectG(response)
            },
            Response.ErrorListener{error ->
                Log.d("WebRequestTest", "that didn't work! " + error.message)
            }
        )

        return jsonObjectRequest
    }


    // obtener json manualmente


    private fun parseObjectG(response: JSONObject) {
        var list = RandomUser.getUser(response)
        val i: Int = 0
        val size: Int = list.size
        for (i in 0 until size) {
            val user = list[i]
            userList.add(user)
        }
        users.value = userList
    }



}
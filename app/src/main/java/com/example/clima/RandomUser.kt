package com.example.clima

import android.util.Log
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*


class RandomUser {

    var name :String?=null
    var Description :String?=null
    var minTemp :String?=null
    var maxTemp :String?=null



    companion object {
        var g = Gson()
        fun getUser(response: JSONObject): ArrayList<RandomUser> {
            val jsonArrayResults: JSONArray = response.getJSONArray("list")
            val size: Int = jsonArrayResults.length()
            val i: Int = 0
            val array = ArrayList<RandomUser>()

            for (i in 0..size - 1) {
                //poner id tambien
                val cityObject = jsonArrayResults.getJSONObject(i)
                val name = cityObject.getString("name")
                val weatherObject = cityObject.getJSONArray("weather").getJSONObject(0)
                val descriptionWeatherObject = weatherObject.getString("description")
                val main = cityObject.getJSONObject("main")
                val minTemp = main.getString("temp_min")+"ºC"
                val maxTemp = main.getString("temp_max")+"ºC"
                val city = RandomUser()
                city.name = name
                city.Description = descriptionWeatherObject
                city.minTemp = minTemp
                city.maxTemp = maxTemp
                array.add(city)

                Log.d("JSONParsing", name + " " + minTemp + weatherObject + descriptionWeatherObject)


            }
            return array
        }


        fun getDay(response: JSONObject): ArrayList<RandomUser> {
            val jsonArrayResults: JSONArray = response.getJSONArray("list")
            val size: Int = jsonArrayResults.length()
            var i: Int = 0
            val array = ArrayList<RandomUser>()
            var antDay = ""
            var name: String
            while (i < size) {
                val cityObject = jsonArrayResults.getJSONObject(i)
                var dayNumber = cityObject.getString("dt_txt")
                var dayNumberList: List<String> = dayNumber.split(" ")
                if(dayNumberList[0] != antDay){
                    antDay = dayNumberList[0]
                    var dayNumberChangeformat: List<String> = dayNumberList[0].split("-")
                    name = dayNumberChangeformat[2] + "-" + dayNumberChangeformat[1]

                    val weatherObject = cityObject.getJSONArray("weather").getJSONObject(0)
                    val descriptionWeatherObject = weatherObject.getString("description")
                    val main = cityObject.getJSONObject("main")
                    val minTemp = main.getString("temp_min")+"ºC"
                    val maxTemp = main.getString("temp_max")+"ºC"
                    val city = RandomUser()
                    city.name = name
                    city.Description = descriptionWeatherObject
                    city.minTemp = minTemp
                    city.maxTemp = maxTemp
                    array.add(city)
                    Log.d("JSONParsingDays", " " + minTemp + weatherObject + descriptionWeatherObject)
                }
                i = i + 1

            }
            return array
        }
    }
}
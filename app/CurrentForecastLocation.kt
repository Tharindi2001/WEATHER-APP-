package com.example.myapplication
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
class MainActivity : AppCompatActivity() {
    val CITY: String = "new zealand"
    val API: String = "dc6d0c4421d5cbac3e13f7785dbbef38"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        weatherTask().execute()
    }
    inner class weatherTask() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            /* Showing the ProgressBar, Making the main design GONE */
            findViewById<ProgressBar>(R.id.loader).visibility = View.VISIBLE
            findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.GONE
            findViewById<TextView>(R.id.errorText).visibility = View.GONE
        }
        override fun doInBackground(vararg params: String?): String? {
            var response:String?
            try{
                response =
                    URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=$API").readTex
                t(
                    Charsets.UTF_8
                )
            }catch (e: Exception){
                response = null
            }
            return response
        }
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                /* Extracting JSON returns from the API */
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val wind = jsonObj.getJSONObject("wind")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)
                val updatedAt:Long = jsonObj.getLong("dt")
                val updatedAtText = "Updated at: "+ SimpleDateFormat("dd/MM/yyyy hh:mm a",
                    Locale.ENGLISH).format(Date(updatedAt*1000))
                val temp = main.getString("temp")+"°C"
                val tempMin = "Min Temp: " + main.getString("temp_min")+"°C"
                val tempMax = "Max Temp: " + main.getString("temp_max")+"°C"
                val pressure = main.getString("pressure")
                val humidity = main.getString("humidity")
                val sunrise:Long = sys.getLong("sunrise")
                val sunset:Long = sys.getLong("sunset")
                val windSpeed = wind.getString("speed")
                val weatherDescription = weather.getString("description")
                val address = jsonObj.getString("name")+", "+sys.getString("country")
                /* Populating extracted data into our views */
                findViewById<TextView>(R.id.address).text = address
                findViewById<TextView>(R.id.updated_at).text = updatedAtText
                findViewById<TextView>(R.id.status).text = weatherDescription.capitalize()
                findViewById<TextView>(R.id.temp).text = temp
                findViewById<TextView>(R.id.temp_min).text = tempMin
                findViewById<TextView>(R.id.temp_max).text = tempMax
                findViewById<TextView>(R.id.sunrise).text = SimpleDateFormat("hh:mm a",
                    Locale.ENGLISH).format(Date(sunrise*1000))
                findViewById<TextView>(R.id.sunset).text = SimpleDateFormat("hh:mm a",
                    Locale.ENGLISH).format(Date(sunset*1000))
                findViewById<TextView>(R.id.wind).text = windSpeed
                findViewById<TextView>(R.id.pressure).text = pressure
                findViewById<TextView>(R.id.humidity).text = humidity
                /* Views populated, Hiding the loader, Showing the main design */
                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
                findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.VISIBLE
            } catch (e: Exception) {
                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
                findViewById<TextView>(R.id.errorText).visibility = View.VISIBLE
            }
        }
    }
}
XML
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
android:orientation="vertical"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:padding="25dp"
android:background="@drawable/gradient_bg">
<RelativeLayout android:id="@+id/mainContainer" android:layout_width="match_parent"
android:layout_height="match_parent" android:visibility="visible">
<LinearLayout android:id="@+id/addressContainer" android:layout_width="match_parent"
android:layout_height="wrap_content" android:orientation="vertical"
android:gravity="center">
<TextView android:id="@+id/address" android:layout_width="wrap_content"
android:layout_height="wrap_content" android:textSize="24dp"
android:text="DHAKA, BD"/>
<TextView android:id="@+id/updated_at" android:layout_width="wrap_content"
android:layout_height="wrap_content" android:textSize="14dp"
android:text="20 April 2012, 20:08 PM"/>
</LinearLayout>
<LinearLayout android:id="@+id/overviewContainer" android:layout_width="match_parent"
android:layout_height="wrap_content" android:orientation="vertical"
android:layout_centerInParent="true">
<TextView android:id="@+id/status" android:layout_width="wrap_content"
android:layout_height="wrap_content" android:textSize="18dp"
android:layout_gravity="center"
android:text="Clear Sky"/>
<TextView android:id="@+id/temp" android:layout_width="wrap_content"
android:layout_height="wrap_content" android:textSize="90dp"
android:fontFamily="sans-serif-thin" android:layout_gravity="center"
android:text="29°C"/>
<LinearLayout android:layout_width="match_parent" android:layout_height="wrap_content"
android:orientation="horizontal" android:gravity="center">
<TextView android:id="@+id/temp_min" android:layout_width="wrap_content"
android:layout_height="wrap_content" android:text="Min Temp: 05:05 AM"/>
<Space android:layout_width="50dp" android:layout_height="wrap_content"/>
<TextView android:id="@+id/temp_max" android:layout_width="wrap_content"
android:layout_height="wrap_content" android:text="Max Temp: 05:05 PM"/>
</LinearLayout>
</LinearLayout>
<LinearLayout android:id="@+id/detailsContainer" android:layout_width="match_parent"
android:layout_height="wrap_content" android:orientation="vertical"
android:layout_alignParentBottom="true">
<LinearLayout android:layout_width="match_parent" android:layout_height="wrap_content"
android:orientation="horizontal" android:weightSum="3">
<LinearLayout android:layout_width="0dp" android:layout_height="wrap_content"
android:orientation="vertical" android:layout_weight="1"
android:gravity="center" android:padding="8dp"
android:background="#3CF1EBF1">
<Space android:layout_width="wrap_content" android:layout_height="5dp"/>
<TextView android:layout_width="wrap_content" android:layout_height="wrap_content"
android:textSize="12dp" android:text="Sunrise"/>
<TextView android:id="@+id/sunrise" android:layout_width="wrap_content"
android:layout_height="wrap_content" android:textSize="14dp"
android:text="06:40 AM"/>
</LinearLayout>
<Space android:layout_width="10dp" android:layout_height="wrap_content"/>
<LinearLayout android:layout_width="0dp" android:layout_height="wrap_content"
android:orientation="vertical" android:layout_weight="1"
android:gravity="center" android:padding="8dp"
android:background="#3CF1EBF1">
<Space android:layout_width="wrap_content" android:layout_height="5dp"/>
<TextView android:layout_width="wrap_content" android:layout_height="wrap_content"
android:textSize="12dp" android:text="Sunset"/>
<TextView android:id="@+id/sunset" android:layout_width="wrap_content"
android:layout_height="wrap_content" android:textSize="14dp"
android:text="06:40 AM"/>
</LinearLayout>
<Space android:layout_width="10dp" android:layout_height="wrap_content"/>
<LinearLayout android:layout_width="0dp" android:layout_height="wrap_content"
android:orientation="vertical" android:layout_weight="1"
android:gravity="center" android:padding="8dp"
android:background="#3CF1EBF1">
<Space android:layout_width="wrap_content" android:layout_height="5dp"/>
<TextView android:layout_width="wrap_content" android:layout_height="wrap_content"
android:textSize="12dp" android:text="Wind"/>
<TextView android:id="@+id/wind" android:layout_width="wrap_content"
android:layout_height="wrap_content" android:textSize="14dp"
android:text="06:40 AM"/>
</LinearLayout>
</LinearLayout>
<Space android:layout_width="wrap_content" android:layout_height="10dp"/>
<LinearLayout android:layout_width="match_parent" android:layout_height="wrap_content"
android:orientation="horizontal" android:weightSum="3">
<LinearLayout android:layout_width="0dp" android:layout_height="wrap_content"
android:orientation="vertical" android:layout_weight="1"
android:gravity="center" android:padding="8dp"
android:background="#c767b9">
<Space android:layout_width="wrap_content" android:layout_height="5dp"/>
<TextView android:layout_width="wrap_content" android:layout_height="wrap_content"
android:textSize="12dp" android:text="Pressure"/>
<TextView android:id="@+id/pressure" android:layout_width="wrap_content"
android:layout_height="wrap_content" android:textSize="14dp"
android:text="06:40 AM"/>
</LinearLayout>
<Space android:layout_width="10dp" android:layout_height="wrap_content"/>
<LinearLayout android:layout_width="0dp" android:layout_height="wrap_content"
android:orientation="vertical" android:layout_weight="1"
android:gravity="center" android:padding="8dp"
android:background="#c767b9">
<Space android:layout_width="wrap_content" android:layout_height="5dp"/>
<TextView android:layout_width="wrap_content" android:layout_height="wrap_content"
android:textSize="12dp" android:text="Humidity"/>
<TextView android:id="@+id/humidity" android:layout_width="wrap_content"
android:layout_height="wrap_content" android:textSize="14dp"
android:text="06:40 AM"/>
</LinearLayout>
<Space android:layout_width="10dp" android:layout_height="wrap_content"/>
<LinearLayout android:layout_width="0dp" android:layout_height="wrap_content"
android:orientation="vertical" android:layout_weight="1"
android:gravity="center" android:padding="8dp"
android:background="#c767b9">
<Space android:layout_width="wrap_content" android:layout_height="5dp"/>
<TextView android:layout_width="wrap_content" android:layout_height="wrap_content"
android:textSize="12dp" android:text="Created By"/>
<TextView android:id="@+id/about" android:layout_width="wrap_content"
android:layout_height="wrap_content" android:textSize="18dp"
android:text="Sandaru"/>
</LinearLayout>
</LinearLayout>
</LinearLayout>
</RelativeLayout>
<ProgressBar android:id="@+id/loader" android:layout_width="wrap_content"
android:layout_height="wrap_content" android:layout_centerInParent="true"
android:visibility="gone"/>
<TextView android:id="@+id/errorText" android:layout_width="wrap_content"
android:layout_height="wrap_content" android:layout_centerInParent="true"
android:visibility="gone" android:text="Something went wrong"/>
</RelativeLayout>
gradient_bg.XML
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
android:shape="rectangle">
<gradient
android:angle="90"
android:startColor="#faf623"
android:endColor="#080b21"
android:type="linear"/>
</shape>

package com.example.myapplication
import android.os.AsyncTask
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
class MainActivity : AppCompatActivity() {
    private val apiKey = "dc6d0c4421d5cbac3e13f7785dbbef38"
    private lateinit var locationAutoCompleteTextView: AutoCompleteTextView
    private lateinit var searchButton: Button
    private lateinit var forecastTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        locationAutoCompleteTextView = findViewById(R.id.locationAutoCompleteTextView)
        searchButton = findViewById(R.id.searchButton)
        forecastTextView = findViewById(R.id.forecastTextView)
        // Set up adapter for AutoCompleteTextView
        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_dropdown_item_1line,
            arrayOf("City1", "City2", "City3") // Add your own list of cities
        )
        locationAutoCompleteTextView.setAdapter(adapter)
        searchButton.setOnClickListener {
            val selectedLocation = locationAutoCompleteTextView.text.toString()
            if (selectedLocation.isNotEmpty()) {
                WeatherTask().execute(selectedLocation)
            }
        }
    }
    inner class WeatherTask : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg params: String?): String? {
            val response: String?
            try {
                val city = params[0] ?: return null
                // API URL for 5-day weather forecast
                val apiUrl = "https://api.openweathermap.org/data/2.5/forecast?q=$city&appid=$apiKey"
                // Make the API call and get the response
                response = URL(apiUrl).readText(Charsets.UTF_8)
            } catch (e: Exception) {
                return null
            }
            return response
        }
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                // Parse the JSON response
                val jsonObj = JSONObject(result)
                val list = jsonObj.getJSONArray("list")
                // Display the forecast for the next 4 days
                forecastTextView.text = "" // Clear previous forecast
                for (i in 0 until 4) {
                    val day = list.getJSONObject(i * 8) // Data for every 3 hours, so skip to the next day
                    val main = day.getJSONObject("main")
                    val temp = main.getString("temp")
                    val dateText = getDateText(day.getString("dt"))
                    val forecast = "$dateText: $temp °C"
                    // Append forecast to the TextView
                    forecastTextView.append("$forecast\n")
                }
                // Display the selected location
                forecastTextView.append("Location: ${locationAutoCompleteTextView.text}")
            } catch (e: Exception) {
                // Handle exception (e.g., JSON parsing error)
            }
        }
        private fun getDateText(timestamp: String): String {
            // Convert timestamp to date format (you can customize the format)
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val date = Date(timestamp.toLong() * 1000)
            return sdf.format(date)
        }
    }
}
XML
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent"
android:layout_height="match_parent"
tools:context=".MainActivity">
<AutoCompleteTextView
android:id="@+id/locationAutoCompleteTextView"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:layout_marginTop="16dp"
android:layout_marginStart="16dp"
android:layout_marginEnd="16dp"
android:hint="Enter Location"
android:completionThreshold="2"/>
<Button
android:id="@+id/searchButton"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:layout_below="@id/locationAutoCompleteTextView"
android:layout_marginTop="8dp"
android:layout_marginEnd="16dp"
android:text="Search"/>
<ScrollView
android:layout_width="match_parent"
android:layout_height="match_parent"
android:layout_below="@id/searchButton"
android:layout_marginTop="16dp">
<TextView
android:id="@+id/forecastTextView"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="Weather Forecast: "
android:textSize="18sp"
android:layout_marginStart="16dp"
android:padding="16dp"/>
</ScrollView>
</RelativeLayout>
Manifest
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
package="com.example.myapplication">
<uses-permission android:name="android.permission.INTERNET"></uses-permission>
<application
android:allowBackup="true"
android:dataExtractionRules="@xml/data_extraction_rules"
android:fullBackupContent="@xml/backup_rules"
android:icon="@mipmap/ic_launcher"
android:label="@string/app_name"
android:roundIcon="@mipmap/ic_launcher_round"
android:supportsRtl="true"
android:theme="@style/Theme.MyApplication"
>
<activity
android:name=".MainActivity"
android:exported="true">
<intent-filter>
<action android:name="android.intent.action.MAIN" />
<category android:name="android.intent.category.LAUNCHER" />
</intent-filter>
</activity>
</application>
</manifest>
package com.example.myapplication
import android.os.AsyncTask
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
class MainActivity : AppCompatActivity() {
    private val apiKey = "dc6d0c4421d5cbac3e13f7785dbbef38"
    private lateinit var locationAutoCompleteTextView: AutoCompleteTextView
    private lateinit var searchButton: Button
    private lateinit var forecastTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        locationAutoCompleteTextView = findViewById(R.id.locationAutoCompleteTextView)
        searchButton = findViewById(R.id.searchButton)
        forecastTextView = findViewById(R.id.forecastTextView)
        // Set up adapter for AutoCompleteTextView
        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_dropdown_item_1line,
            arrayOf("City1", "City2", "City3") // Add your own list of cities
        )
        locationAutoCompleteTextView.setAdapter(adapter)
        searchButton.setOnClickListener {
            val selectedLocation = locationAutoCompleteTextView.text.toString()
            if (selectedLocation.isNotEmpty()) {
                WeatherTask().execute(selectedLocation)
            }
        }
    }
    inner class WeatherTask : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg params: String?): String? {
            val response: String?
            try {
                val city = params[0] ?: return null
                // API URL for 5-day weather forecast
                val apiUrl = "https://api.openweathermap.org/data/2.5/forecast?q=$city&appid=$apiKey"
                // Make the API call and get the response
                response = URL(apiUrl).readText(Charsets.UTF_8)
            } catch (e: Exception) {
                return null
            }
            return response
        }
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                // Parse the JSON response
                val jsonObj = JSONObject(result)
                val list = jsonObj.getJSONArray("list")
                // Display the forecast for the next 4 days
                forecastTextView.text = "" // Clear previous forecast
                for (i in 0 until 4) {
                    val day = list.getJSONObject(i * 8) // Data for every 3 hours, so skip to the next day
                    val main = day.getJSONObject("main")
                    val temp = main.getString("temp")
                    val dateText = getDateText(day.getString("dt"))
                    val forecast = "$dateText: $temp °C"
                    // Append forecast to the TextView
                    forecastTextView.append("$forecast\n")
                }
                // Display the selected location
                forecastTextView.append("Location: ${locationAutoCompleteTextView.text}")
            } catch (e: Exception) {
                // Handle exception (e.g., JSON parsing error)
            }
        }
        private fun getDateText(timestamp: String): String {
            // Convert timestamp to date format (you can customize the format)
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val date = Date(timestamp.toLong() * 1000)
            return sdf.format(date)
        }
    }
}
XML
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent"
android:layout_height="match_parent"
tools:context=".MainActivity">
<AutoCompleteTextView
android:id="@+id/locationAutoCompleteTextView"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:layout_marginTop="16dp"
android:layout_marginStart="16dp"
android:layout_marginEnd="16dp"
android:hint="Enter Location"
android:completionThreshold="2"/>
<Button
android:id="@+id/searchButton"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:layout_below="@id/locationAutoCompleteTextView"
android:layout_marginTop="8dp"
android:layout_marginEnd="16dp"
android:text="Search"/>
<ScrollView
android:layout_width="match_parent"
android:layout_height="match_parent"
android:layout_below="@id/searchButton"
android:layout_marginTop="16dp">
<TextView
android:id="@+id/forecastTextView"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="Weather Forecast: "
android:textSize="18sp"
android:layout_marginStart="16dp"
android:padding="16dp"/>
</ScrollView>
</RelativeLayout>
Manifest
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
package="com.example.myapplication">
<uses-permission android:name="android.permission.INTERNET"></uses-permission>
<application
android:allowBackup="true"
android:dataExtractionRules="@xml/data_extraction_rules"
android:fullBackupContent="@xml/backup_rules"
android:icon="@mipmap/ic_launcher"
android:label="@string/app_name"
android:roundIcon="@mipmap/ic_launcher_round"
android:supportsRtl="true"
android:theme="@style/Theme.MyApplication"
>
<activity
android:name=".MainActivity"
android:exported="true">
<intent-filter>
<action android:name="android.intent.action.MAIN" />
<category android:name="android.intent.category.LAUNCHER" />
</intent-filter>
</activity>
</application>
</manifest>
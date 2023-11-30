// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "2.0.0-Beta1" apply false
}

data class WeatherResponse(
    val list: List<WeatherEntry>
)

data class WeatherEntry(
    val main: Main,
    val weather: List<Weather>,
    val dt_txt: String
)

data class Main(
    val temp: Double,
    val humidity: Int
)

data class Weather(
    val main: String,
    val description: String
)

// WeatherData.kt
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

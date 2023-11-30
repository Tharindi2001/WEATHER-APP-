// WeatherRepository.kt
class WeatherRepository(private val weatherApi: WeatherApi) {
    suspend fun getWeatherForLocation(lat: Double, lon: Double, apiKey: String): WeatherResponse {
        return weatherApi.getWeatherForLocation(lat, lon, apiKey)
    }
}

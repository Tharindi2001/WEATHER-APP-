// WeatherApi.kt
interface WeatherApi {
    @GET("forecast")
    suspend fun getWeatherForLocation(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String
    ): WeatherResponse
}

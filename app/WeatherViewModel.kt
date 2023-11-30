// WeatherViewModel.kt
class WeatherViewModel(application: MyApplication) : ViewModel() {
    private val repository: WeatherRepository = WeatherRepository(application.weatherApi)
    // ...
}

    private val _weatherData = MutableLiveData<WeatherResponse>()
    val weatherData: LiveData<WeatherResponse> get() = _weatherData

    fun getWeatherForLocation(lat: Double, lon: Double, apiKey: String) {
        viewModelScope.launch {
            _weatherData.value = repository.getWeatherForLocation(lat, lon, apiKey)
        }
    }
}

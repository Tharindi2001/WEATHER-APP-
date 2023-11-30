class myActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.your_layout) // Replace with the layout file you are using

        // Assuming you have an array of hourly forecast data (e.g., List<HourlyForecast> hourlyForecasts)
        val hourlyForecastLayout: LinearLayout = findViewById(R.id.hourlyForecastLayout)

        for (forecast in hourlyForecasts) {
            // Create a new TextView for each hourly forecast
            val hourlyTextView = TextView(this)
            hourlyTextView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            // Set the text and other properties based on your HourlyForecast model
            hourlyTextView.text = forecast.condition // Replace with the actual property from your HourlyForecast model
            hourlyTextView.textSize = 16f // Set the text size as needed
            hourlyTextView.setTextColor(ContextCompat.getColor(this, R.color.your_text_color)) // Set the text color

            // Add the TextView to the hourlyForecastLayout
            hourlyForecastLayout.addView(hourlyTextView)
        }
    }
}

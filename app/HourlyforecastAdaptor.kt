import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HourlyForecastAdapter(private val hourlyForecastItems: List<ForecastItem>) :
    RecyclerView.Adapter<HourlyForecastAdapter.HourlyForecastViewHolder>() {

    class HourlyForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyForecastViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_detailed_forecast, parent, false)
        return HourlyForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: HourlyForecastViewHolder, position: Int) {
        val currentItem = hourlyForecastItems[position]

        // Bind data to the views in the item layout
        holder.itemView.apply {
            textViewTime.text = currentItem.time
            textViewTemperature.text = currentItem.temperature
            textViewRealFeel.text = currentItem.realFeel
            textViewWindSpeed.text = currentItem.windSpeed
            textViewHumidity.text = currentItem.humidity
            textViewUVIndex.text = currentItem.uvIndex
            imageViewWeatherIcon.setImageResource(currentItem.weatherIcon)
        }
    }

    override fun getItemCount(): Int {
        return hourlyForecastItems.size
    }
}

}
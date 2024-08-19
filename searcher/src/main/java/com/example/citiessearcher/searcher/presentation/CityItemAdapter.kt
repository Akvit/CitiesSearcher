package com.example.citiessearcher.searcher.presentation

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.citiessearcher.searcher.R
import com.example.citiessearcher.searcher.databinding.CityItemBinding
import com.example.citiessearcher.searcher.domain.models.CityModel
import com.example.citiessearcher.searcher.domain.models.CoordinatesModel
import com.example.citiessearcher.searcher.presentation.CityItemAdapter.CityItemViewHolder
import java.util.Locale

class CityItemAdapter(
) : RecyclerView.Adapter<CityItemViewHolder>() {

    var cities: List<CityModel> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityItemViewHolder {
        val binding = CityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityItemViewHolder, position: Int) {
        holder.bind(cities[position])
    }

    override fun getItemCount() = cities.size

    inner class CityItemViewHolder(
        private val binding: CityItemBinding
    ) : ViewHolder(binding.root) {

        fun bind(model: CityModel) {
            with(binding) {

                titleTextView.text = titleTextView.context.resources
                    .getString(R.string.city_and_country, model.name, model.country)

                longtextView.text = longtextView.resources
                    .getString(R.string.longitude, model.coord.longitude)

                lattextView.text = lattextView.resources
                    .getString(R.string.latitude, model.coord.latitude)

                root.setOnClickListener {
                    val uri = model.coord.toIntentFormat()
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                    it.context.startActivity(intent)
                }
            }
        }
    }
}

fun CoordinatesModel.toIntentFormat() = String.format(
    Locale.ENGLISH,
    "geo:%s,%s", this.latitude, this.longitude
)

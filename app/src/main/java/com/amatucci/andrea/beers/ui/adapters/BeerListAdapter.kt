package com.amatucci.andrea.beers.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.amatucci.andrea.beers.R
import com.amatucci.andrea.beers.data.model.Beer
import com.amatucci.andrea.beers.databinding.ListItemBeerBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.util.*
import kotlin.collections.ArrayList

class BeerListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val beers = ArrayList<Beer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BeerViewHolder(
            ListItemBeerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val beer = beers[position]
        (holder as BeerViewHolder).bind(beer)
    }

    override fun getItemCount(): Int {
        return beers.size
    }

    class BeerViewHolder(private val binding: ListItemBeerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(beer : Beer) {
            binding.apply {
                beerBlendColor.setBackgroundColor(ContextCompat.getColor(root.context, android.R.color.transparent))
                beer.let {
                    txtBeerName.text = it.name.toUpperCase(Locale.getDefault())
                    txtTagline.text = it.tagLine
                    txtId.text = it.id.toString()
                    txtFirstBrewed.text = it.firstBrewed
                    Glide.with(root)
                        .load(it.imageUrl)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.beer_ph)
                        .centerInside()
                        .into(ivBeer)
                }
            }
        }
    }


}
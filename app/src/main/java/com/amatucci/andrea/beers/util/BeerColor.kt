package com.amatucci.andrea.beers.util

import com.amatucci.andrea.beers.R

class BeerColor {

    companion object {
        private val colorList = listOf(
            R.color.beer_srm_1,
            R.color.beer_srm_5,
            R.color.beer_srm_10,
            R.color.beer_srm_15,
            R.color.beer_srm_20,
            R.color.beer_srm_25,
            R.color.beer_srm_30,
            R.color.beer_srm_35,
            R.color.beer_srm_40
        )

        fun getColorResourceId(srm: Double): Int {
            if (srm >= 40.0) return R.color.beer_srm_40
            val i : Int = (srm / 5.0).toInt()
            return colorList[i]
        }
    }


}
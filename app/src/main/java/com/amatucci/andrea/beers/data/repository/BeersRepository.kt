package com.amatucci.andrea.beers.data.repository

import com.amatucci.andrea.beers.data.network.BeersApi

class BeersRepository(private val beersApi: BeersApi) {

    suspend fun getBeers(page: Int, after: String? = null, before: String? = null) = beersApi.getBeers(page, after, before)

}
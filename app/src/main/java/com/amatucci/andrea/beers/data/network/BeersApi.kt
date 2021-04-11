package com.amatucci.andrea.beers.data.network

import com.amatucci.andrea.beers.data.model.Beer
import retrofit2.http.GET
import retrofit2.http.Query

interface BeersApi {

    @GET("beers")
    suspend fun getBeers(
        @Query("page") page: Int? = 1,
        @Query("brewed_after") brewedAfter: String? = null,
        @Query("brewed_before") brewedBefore: String? = null
    ): List<Beer>

}
package com.amatucci.andrea.beers.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.amatucci.andrea.beers.data.model.Beer
import com.amatucci.andrea.beers.data.network.BeersApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BeersRepository(private val beersApi: BeersApi) {

    val beers = MutableLiveData<List<Beer>>()

    fun getBeers(page: Int = 1) {
        val call = beersApi.getBeers(page)
        call.enqueue(object : Callback<List<Beer>> {
            override fun onResponse(call: Call<List<Beer>>, response: Response<List<Beer>>) {
                Log.d("BeersRepository","success")
                if (response.isSuccessful){
                    beers.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<Beer>>, t: Throwable) {
                Log.d("BeersRepository","error")
            }

        })
    }

}
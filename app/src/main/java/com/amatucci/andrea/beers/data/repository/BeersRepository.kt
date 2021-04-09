package com.amatucci.andrea.beers.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.amatucci.andrea.beers.data.model.Beer
import com.amatucci.andrea.beers.data.network.BeersApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BeersRepository(private val beersApi: BeersApi) {

    //val beers = MutableLiveData<List<Beer>>()
    //val loading = MutableLiveData(false)

    suspend fun getBeers(page: Int) : List<Beer> {
//        val res = MutableLiveData<List<Beer>>()
//        val call = beersApi.getBeers(page)
//        loading.value = true
//        call.enqueue(object : Callback<List<Beer>> {
//            override fun onResponse(call: Call<List<Beer>>, response: Response<List<Beer>>) {
//                Log.d("BeersRepository","success")
//                loading.postValue(false)
//                if (response.isSuccessful){
//                    res.postValue(response.body())
//                }
//            }
//
//            override fun onFailure(call: Call<List<Beer>>, t: Throwable) {
//                Log.d("BeersRepository","error")
//                loading.postValue(false)
//            }
//
//        })

        return beersApi.getBeers(page)
    }

//    fun getBeers(page: Int) {
//        val call = beersApi.getBeers(page)
//        loading.value = true
//        call.enqueue(object : Callback<List<Beer>> {
//            override fun onResponse(call: Call<List<Beer>>, response: Response<List<Beer>>) {
//                Log.d("BeersRepository","success")
//                loading.postValue(false)
//                if (response.isSuccessful){
//                    beers.postValue(response.body())
//                }
//            }
//
//            override fun onFailure(call: Call<List<Beer>>, t: Throwable) {
//                Log.d("BeersRepository","error")
//                loading.postValue(false)
//            }
//
//        })
//    }

}
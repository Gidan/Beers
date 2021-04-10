package com.amatucci.andrea.beers.data.repository

import com.amatucci.andrea.beers.data.model.Beer
import com.amatucci.andrea.beers.data.network.BeersApi


class BeersRepository(private val beersApi: BeersApi) {

    //val beers = MutableLiveData<List<Beer>>()
    //val loading = MutableLiveData(false)

    suspend fun getBeers(page: Int, after: String? = null, before: String? = null) = beersApi.getBeers(page, after, before)

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
package com.amatucci.andrea.beers.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amatucci.andrea.beers.data.repository.BeersRepository

class BeersViewModel(private val beersRepository: BeersRepository) : ViewModel() {

    private var _lastPage = 0
    val loading : LiveData<Boolean> = beersRepository.loading

    val beers = beersRepository.beers

    init {
        nextPage()
    }

    fun nextPage() {
        beersRepository.getBeers(++_lastPage)
    }

    fun refresh(){
        _lastPage = 0
        nextPage()
    }


}
package com.amatucci.andrea.beers.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amatucci.andrea.beers.data.repository.BeersRepository

class BeersViewModel(private val beersRepository: BeersRepository) : ViewModel() {

    private val _lastPage = MutableLiveData(0)

    val beers = beersRepository.beers

    init {
        nextPage()
    }

    fun nextPage() {
        beersRepository.getBeers(_lastPage.value!! + 1)
    }


}
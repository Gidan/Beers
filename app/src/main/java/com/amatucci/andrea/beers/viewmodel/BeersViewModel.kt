package com.amatucci.andrea.beers.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.amatucci.andrea.beers.data.model.Beer
import com.amatucci.andrea.beers.data.repository.BeersRepository
import kotlinx.coroutines.Dispatchers

class BeersViewModel(private val beersRepository: BeersRepository) : ViewModel() {

    private val page = MutableLiveData(1)
    val after = MutableLiveData<String>()
    val before = MutableLiveData<String>()
    private var lastPage = 0

    val loading = MutableLiveData(false)

    private val list = ArrayList<Beer>()
    private val _beers = MutableLiveData<List<Beer>>(list)
    val beers : LiveData<List<Beer>> = _beers

    var beerPage = Transformations.switchMap(page){
        fetchPage(it)
    }

    init {
        beerPage.observeForever{
            it?.let {
                list.addAll(it)
                _beers.value = list
            }
        }

        after.observeForever {
            refresh()
        }
        before.observeForever {
            refresh()
        }

        nextPage()
    }

    fun nextPage() {
        page.value = lastPage + 1
    }

    private fun fetchPage(page: Int) = liveData(Dispatchers.IO) {
        if (!loading.value!!){
            Log.d("BeersViewModel", "request page $page")
            loading.postValue(true)
            try {
                emit(beersRepository.getBeers(page, after.value, before.value))
                lastPage = page
            } catch (exception: Exception) {
                Log.e("BeersViewModel", exception.message ?: "fetch error")
                emit(null)
            }
            loading.postValue(false)
        }
    }

    fun refresh(){
        list.clear()
        lastPage = 0
        nextPage()
    }


}
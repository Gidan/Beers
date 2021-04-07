package com.amatucci.andrea.beers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.amatucci.andrea.beers.data.model.Beer
import com.amatucci.andrea.beers.viewmodel.BeersViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val vm: BeersViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm.beers.observe(this){
            Log.d("MainActivity","changed list " + it.size)
            it.forEach{b -> Log.d("MainActivity", b.toString())}
        }
    }
}
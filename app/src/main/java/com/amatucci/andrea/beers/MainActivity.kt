package com.amatucci.andrea.beers

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.amatucci.andrea.beers.databinding.ActivityMainBinding
import com.amatucci.andrea.beers.viewmodel.BeersViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val vm: BeersViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navController)

        vm.beers.observe(this){
            Log.d("MainActivity","changed list " + it.size)
            it.forEach{b -> Log.d("MainActivity", b.toString())}
        }
    }
}
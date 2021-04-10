package com.amatucci.andrea.beers

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.amatucci.andrea.beers.databinding.ActivityMainBinding
import com.amatucci.andrea.beers.ui.MonthYearPickerDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navController)

//        binding.toolbar.setOnMenuItemClickListener(object : androidx.appcompat.widget.Toolbar.OnMenuItemClickListener {
//            override fun onMenuItemClick(item: MenuItem?): Boolean {
//                return when (item!!.itemId) {
//                    R.id.action_filter -> {
//                        Log.d("MainActivity", "filter pressed")
//                        MonthYearPickerDialog().apply {
//                            setListener { view, year, month, dayOfMonth ->
//                                Toast.makeText(requireContext(), "Set date: $year/$month/$dayOfMonth", Toast.LENGTH_LONG).show()
//                            }
//                            show(supportFragmentManager, "MonthYearPickerDialog")
//                        }
//                        true
//                    }
//
//                    else -> {
//                        return false
//                    }
//                }
//            }
//        })
    }



}
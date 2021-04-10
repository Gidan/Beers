package com.amatucci.andrea.beers.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amatucci.andrea.beers.R
import com.amatucci.andrea.beers.databinding.FragmentBeersListBinding
import com.amatucci.andrea.beers.ui.adapters.BeerListAdapter
import com.amatucci.andrea.beers.util.PaginationListener
import com.amatucci.andrea.beers.viewmodel.BeersViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import org.koin.android.viewmodel.ext.android.viewModel

class BeersListFragment : Fragment() {

    private val beersViewModel: BeersViewModel by viewModel()
    private lateinit var binding : FragmentBeersListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = FragmentBeersListBinding.inflate(layoutInflater)
        setup()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu);
    }

    private fun setup(){
        val adapter = BeerListAdapter()
        binding.beersList.adapter = adapter
        val layoutManager = LinearLayoutManager(context)
        binding.beersList.layoutManager = layoutManager
        binding.beersList.addOnScrollListener(object : PaginationListener(layoutManager){
            override fun loadNextPage() {
                beersViewModel.nextPage()
            }

            override fun isLoading(): Boolean {
                return beersViewModel.loading.value ?: false
            }
        })

        beersViewModel.beers.observe(viewLifecycleOwner){
            binding.swipeRefresh.isRefreshing = false
            adapter.beers.clear()
            adapter.beers.addAll(it)
            adapter.notifyDataSetChanged()
        }

        binding.swipeRefresh.setOnRefreshListener {
            adapter.beers.clear()
            adapter.notifyDataSetChanged()
            beersViewModel.refresh()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_filter -> {
            Log.d("MainActivity", "filter pressed")
            val builder = MaterialDatePicker.Builder.dateRangePicker()
                builder.setTitleText("Select date")
                builder.setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            val picker = builder.build()
            picker.show(activity?.supportFragmentManager!!, picker.toString())
//            MonthYearPickerDialog().apply {
//                setListener { _, year, month, dayOfMonth ->
//                    val strMonth = month.toString().padStart(2, '0')
//                    beersViewModel.after.value = "$strMonth-$year"
//                }
//                show(this@BeersListFragment.parentFragmentManager, "MonthYearPickerDialog")
//            }
            true
        }

        else -> {
            false
        }
    }

}
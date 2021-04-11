package com.amatucci.andrea.beers.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.amatucci.andrea.beers.databinding.FragmentBeersListBinding
import com.amatucci.andrea.beers.ui.adapters.BeerListAdapter
import com.amatucci.andrea.beers.util.PaginationListener
import com.amatucci.andrea.beers.viewmodel.BeersViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class BeersListFragment : Fragment() {

    private val beersViewModel: BeersViewModel by viewModel()
    private lateinit var binding : FragmentBeersListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBeersListBinding.inflate(layoutInflater)
        setup()
        return binding.root
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

        binding.filterAfter.setOnClickListener {
            MonthYearPickerDialog().apply {
                setListener { _, year, month, _ ->
                    val strMonth = month.toString().padStart(2, '0')
                    beersViewModel.after.value = "$strMonth-$year"
                }
                show(this@BeersListFragment.parentFragmentManager, "MonthYearPickerDialog")
            }
        }

        binding.filterBefore.setOnClickListener {
            MonthYearPickerDialog().apply {
                setListener { _, year, month, _ ->
                    val strMonth = month.toString().padStart(2, '0')
                    beersViewModel.before.value = "$strMonth-$year"
                }
                show(this@BeersListFragment.parentFragmentManager, "MonthYearPickerDialog")
            }
        }

        beersViewModel.after.observe(viewLifecycleOwner){
            binding.filterAfterValue.text = it
        }
        beersViewModel.before.observe(viewLifecycleOwner){
            binding.filterBeforeValue.text = it
        }
    }

}
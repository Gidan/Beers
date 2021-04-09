package com.amatucci.andrea.beers.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    }

}
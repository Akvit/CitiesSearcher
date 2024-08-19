package com.example.citiessearcher.searcher.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import com.example.citiessearcher.searcher.R
import com.example.citiessearcher.searcher.databinding.FragmentCitiesListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CitiesListFragment : Fragment() {

    private val viewModel: CitiesViewModel by viewModel()
    private val adapter: CityItemAdapter by lazy { CityItemAdapter() }

    private var _binding: FragmentCitiesListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCitiesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initLiveData()
        viewModel.startLoading()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun initViews() =
        with(binding) {
            recyclerView.adapter = adapter
            toolbar.inflateMenu(R.menu.menu)
            initSearchView()
        }

    private fun initSearchView() {
        val searchItem = binding.toolbar.menu.findItem(R.id.action_search).actionView as SearchView
        searchItem.queryHint = getString(R.string.type_here)
        searchItem.setOnQueryTextListener(object : OnQueryTextListener,
            SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.startLoading(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String?) = true
        })
    }

    private fun initLiveData() {
        viewModel.citiesLiveData.observe(viewLifecycleOwner) { adapter.cities = it }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.recyclerView.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.recyclerView.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { errorText ->
            Toast.makeText(requireContext(), errorText, Toast.LENGTH_LONG).show()
        }
    }
}
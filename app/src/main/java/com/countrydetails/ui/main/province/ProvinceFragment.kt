package com.countrydetails.ui.main.province

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.countrydetails.databinding.MainFragmentBinding
import com.countrydetails.utils.Resource
import com.countrydetails.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProvinceFragment : Fragment() {

    private var binding: MainFragmentBinding by autoCleared()
    private val viewModel: ProvinceViewModel by viewModels()
    private lateinit var adapter: ProvinceAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
        viewModel.start(arguments?.getInt("id"), arguments?.getString("code"))
    }

    private fun setupRecyclerView() {
        adapter = ProvinceAdapter(requireContext())
        binding.charactersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.charactersRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.provinces.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    if (!it.data.isNullOrEmpty()) {
                        binding.noData.visibility = View.GONE
                        adapter.setData(it.data)
                    } else binding.noData.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                }

                Resource.Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                }

                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })
    }
}

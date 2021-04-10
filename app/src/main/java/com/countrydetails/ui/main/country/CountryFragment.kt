package com.countrydetails.ui.main.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.countrydetails.R
import com.countrydetails.databinding.MainFragmentBinding
import com.countrydetails.utils.Resource
import com.countrydetails.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryFragment : Fragment(), Adapter.ArticleItemListener {

    private var binding: MainFragmentBinding by autoCleared()
    private val viewModel: CountryViewModel by viewModels()
    private lateinit var adapter: Adapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = Adapter(requireContext(), this)
        binding.charactersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.charactersRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.articles.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) {
                        binding.noData.visibility = View.GONE
                        adapter.setData(it.data)
                    } else binding.noData.visibility = View.VISIBLE
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onClickedArticle(articleId: Int, code: String) {
        findNavController().navigate(
            R.id.action_charactersFragment_to_characterDetailFragment,
            bundleOf("id" to articleId, "code" to code)
        )
    }

}

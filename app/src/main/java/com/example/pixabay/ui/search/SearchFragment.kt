package com.example.pixabay.ui.search

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.pixabay.R
import com.example.pixabay.core.Constants.INITIAL_SEARCH_QUERY
import com.example.pixabay.core.isOnline
import com.example.pixabay.databinding.SearchFragmentBinding
import com.example.pixabay.domain.model.Hit
import com.example.pixabay.ui.search.adapter.SearchAdapter
import com.example.pixabay.ui.search.listener.SearchItemClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(), SearchItemClickListener {

    private val searchViewModel: SearchViewModel by activityViewModels()

    private lateinit var binding: SearchFragmentBinding
    private lateinit var searchAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SearchFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@SearchFragment
            viewModel = searchViewModel
        }

        searchViewModel.searchImagesAsync(
            searchViewModel.lastQuery ?: INITIAL_SEARCH_QUERY,
            requireContext().isOnline()
        )

        initAdapter()
        initSearchQueryListener()
        initObserver()

        return binding.root
    }

    private fun initAdapter() {
        searchAdapter = SearchAdapter(this)
        binding.recyclerView.adapter = searchAdapter
    }

    private fun initSearchQueryListener() {
        binding.searchView.apply {
            setOnQueryTextListener(
                object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String): Boolean {
                        searchViewModel.searchImagesAsync(query, requireContext().isOnline())
                        dismissKeyboard(this@apply)
                        return true
                    }

                    override fun onQueryTextChange(newText: String): Boolean {
                        return true
                    }
                }
            )
        }
    }

    private fun initObserver() {
        searchViewModel.searchHits.observe(viewLifecycleOwner) {
            searchAdapter.submitList(it)
        }
    }

    override fun onItemClicked(hit: Hit) {
        showHitDetailsAlert(hit)
    }

    private fun showHitDetailsAlert(hit: Hit) {
        AlertDialog.Builder(requireContext()).apply {
            setTitle(R.string.alert_title)
            setMessage(R.string.alert_message)
            setPositiveButton(R.string.alert_yes) { _, _ -> showDetailFragment(hit) }
            setNegativeButton(R.string.alert_no) { _, _ -> }
        }.show()

    }

    private fun showDetailFragment(hit: Hit) {
        val action = SearchFragmentDirections.actionGlobalToDetailsFragment(hit)
        findNavController().navigate(action)
    }

    private fun dismissKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
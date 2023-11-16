package com.example.news.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.adapter.NewsAdapter
import com.example.news.databinding.FragmentDetailBinding
import com.example.news.databinding.FragmentFeedBinding
import com.example.news.presentation.viewmodel.FeedViewModel
import com.example.news.util.BaseFragment
import com.example.news.util.Resource
import com.example.news.util.Resource.Loading


class FeedFragment : BaseFragment<FragmentFeedBinding>(
    onInflate = FragmentFeedBinding::inflate
) {
    private val viewModel: FeedViewModel by viewModels()
    private val newsAdapter by lazy {
        NewsAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setViewModelObservers()
        passDataToDetailFragment()

    }

    private fun setAdapter() {

        binding.recyclerViewFeed1.layoutManager =
            LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false)
        binding.recyclerViewFeed1.adapter = newsAdapter
    }

    private fun setViewModelObservers() {
        viewModel.newsList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    progressBarVisibility()
                    response.data?.let { newsResponse ->
                        Log.d("TEST RESPONSE RESULT", "response ${response.data}: ")
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }


                is Resource.Error -> {
                    progressBarVisibility()
                    response.message?.let { message ->
                        Log.e("News Fragment", "Error $message")

                    }
                }

                is Loading -> {
                    progressBarVisibility()
                }
            }

        }
    }

    private fun progressBarVisibility() {

        binding.progressBar.apply {
            if (visibility == View.VISIBLE) {
                visibility = View.GONE
            }
        }
    }

    private fun passDataToDetailFragment() {
        newsAdapter.onItemClickListener = {
            findNavController().navigate(
                FeedFragmentDirections.actionFeedFragmentToDetailFragment(
                    it
                )
            )


        }
    }
}






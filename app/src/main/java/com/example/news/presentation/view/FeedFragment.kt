package com.example.news.presentation.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.adapter.NewsAdapter
import com.example.news.adapter.NewsTopAdapter
import com.example.news.databinding.FragmentFeedBinding
import com.example.news.presentation.viewmodel.FeedViewModel
import com.example.news.util.BaseFragment
import com.example.news.util.Resource
import com.example.news.util.Resource.Loading
import com.example.news.util.Utils
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class FeedFragment() : BaseFragment<FragmentFeedBinding>(
    onInflate = FragmentFeedBinding::inflate
) {

    private val feedViewModel by viewModels<FeedViewModel>()


    private val newsTopAdapter: NewsTopAdapter by lazy {
        NewsTopAdapter()
    }

    private val newsAdapter by lazy {
        NewsAdapter()
    }
    private lateinit var sharedPreferences : SharedPreferences
    private var lang="en"
    private var query=""

    override fun onCreateView(
        
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedPreferences = activity?.getSharedPreferences(Utils.sharedPreferences, Context.MODE_PRIVATE)!!
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        setViewModelObservers()
        setClickListener()
        setSearchView()
        setLanguage()


    }

    private fun setAdapter() {

        binding.recyclerViewFeed1.layoutManager =
            LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false)
        binding.recyclerViewFeed1.adapter = newsTopAdapter

        binding.recyclerViewFeed2.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerViewFeed2.adapter = newsAdapter

    }

    private fun setViewModelObservers() {
        feedViewModel.newsList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    progressBarVisibility()
                    response.data?.let { newsResponse ->
                        Log.d("TEST RESPONSE RESULT", "response ${response.data}: ")
                        newsTopAdapter.differ.submitList(newsResponse.articles)
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

        feedViewModel.searchNewsList.observe(viewLifecycleOwner) { response ->
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

    private fun setClickListener() {
        newsTopAdapter.onItemClickListener = {
            findNavController().navigate(
                FeedFragmentDirections.actionFeedFragmentToDetailFragment(it)

            )
        }

        newsAdapter.onItemClickListener = {
            findNavController().navigate(
                FeedFragmentDirections.actionFeedFragmentToDetailFragment(it)
            )
        }

        binding.langText.setOnClickListener {
            findNavController().navigate(FeedFragmentDirections.actionFeedFragmentToLanguageFragment())
        }

    }


    private fun setSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                feedViewModel.searchNews(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                query = newText
                return false
            }

        })
    }

    private fun setLanguage(){
        lang = sharedPreferences.getString(Utils.language, "en").toString()
        feedViewModel.getNews( lang)
        binding.langText.text = lang.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.ROOT
            ) else it.toString()
        }


    }

}







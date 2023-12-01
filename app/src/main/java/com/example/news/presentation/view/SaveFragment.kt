package com.example.news.presentation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.adapter.NewsAdapter
import com.example.news.databinding.FragmentSaveBinding
import com.example.news.presentation.viewmodel.SaveViewModel
import com.example.news.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SaveFragment : BaseFragment<FragmentSaveBinding>(
    onInflate = FragmentSaveBinding::inflate
) {

    private val saveViewModel by viewModels<SaveViewModel>()
    private val newsAdapter by lazy {
        NewsAdapter()
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()


    }

//    fun setupListeners() {
//        newsAdapter.onItemClickListener = {
//            findNavController().navigate(
//                SaveFragmentDirections.actionSaveFragmentToDetailFragment(it)
//            )
//        }
//    }

    private fun setAdapter() {
        binding.recyclerViewSave.layoutManager =
            LinearLayoutManager(requireActivity())
        binding.recyclerViewSave.adapter = newsAdapter
        saveViewModel.getSavedNews()
//        newsAdapter.setLocalList(newsAdapter.list)

    }
}
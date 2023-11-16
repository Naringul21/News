package com.example.news.presentation.view

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.news.databinding.FragmentDetailBinding
import com.example.news.util.BaseFragment

class DetailFragment : BaseFragment<FragmentDetailBinding>(
    onInflate = FragmentDetailBinding::inflate
) {

    val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.setupViews()


    }
    private fun FragmentDetailBinding.setupViews() {
       val articles = args.article
        detailAuthor.text = articles.author.toString()
        detailContent.text = articles.content.toString()
        detailDescription.text = articles.description.toString()
        detailTitle.text = articles.title.toString()
        Glide.with(root).load(articles.urlToImage).into(detailMedia)
    }

}
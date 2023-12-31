package com.example.news.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.data.remote.model.Articles
import com.example.news.databinding.RecyclerItemNewsBinding

class NewsAdapter() : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    inner class NewsViewHolder(private val binding: RecyclerItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Articles) {
            binding.publishedData.text = data.publishedAt
            binding.newsAuthor.text = data.author
            binding.newsTitle.text = data.title
            Glide.with(binding.root).load(data.urlToImage).into(binding.newsMedia)
        }
    }


    private val differCallBack = object : DiffUtil.ItemCallback<Articles>() {
        override fun areItemsTheSame(oldItem: Articles, newItem: Articles): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Articles, newItem: Articles): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerItemNewsBinding.inflate(inflater,parent,false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val articles = differ.currentList[position]
        holder.bind(articles)

        Log.e("list", "$articles")

        holder.itemView.setOnClickListener {
            onItemClickListener(articles)
        }
    }


    var onItemClickListener: ((Articles) -> Unit) ={}
}
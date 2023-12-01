package com.example.news.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.data.remote.model.Articles
import com.example.news.databinding.RecyclerItemTopBinding

class NewsTopAdapter : RecyclerView.Adapter<NewsTopAdapter.NewsTopViewHolder>() {

    inner class NewsTopViewHolder(private val binding: RecyclerItemTopBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Articles) {
            binding.newsTitleTop.text = data.title
            Glide.with(binding.root).load(data.urlToImage).into(binding.newsMediaTop)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsTopViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerItemTopBinding.inflate(inflater,parent,false)
        return NewsTopViewHolder(binding)


    }

    override fun onBindViewHolder(holder: NewsTopViewHolder, position: Int) {
        val articles = differ.currentList[position]
        holder.bind(articles)

        Log.e("list", "$articles")

        holder.itemView.setOnClickListener {
            onItemClickListener(articles)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    var onItemClickListener: ((Articles) -> Unit) ={}




}
package com.example.news.presentation.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.news.databinding.FragmentLanguageBinding
import com.example.news.util.BaseFragment
import com.example.news.util.Utils

class LanguageFragment : BaseFragment<FragmentLanguageBinding> (
    FragmentLanguageBinding ::inflate
) {
    private lateinit var  sharedPreferences: SharedPreferences


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

        binding.langEn.setOnClickListener{
            sharedPreferences.edit().putString(Utils.language, "en").apply()
            findNavController().popBackStack()
        }

        binding.langRu.setOnClickListener {
            sharedPreferences.edit().putString(Utils.language, "ru").apply()
            findNavController().popBackStack()
        }

        binding.btnBack.setOnClickListener{
            findNavController().popBackStack()
        }
    }
    }

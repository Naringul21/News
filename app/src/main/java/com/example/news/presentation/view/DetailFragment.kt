package com.example.news.presentation.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.PopupMenu
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.data.remote.model.toSavedNews
import com.example.news.databinding.FragmentDetailBinding
import com.example.news.presentation.viewmodel.DetailViewModel
import com.example.news.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(
    onInflate = FragmentDetailBinding::inflate
) {
    private val detailViewModel by viewModels<DetailViewModel>()
    private val args: DetailFragmentArgs by navArgs()
    private var isSaved: Boolean = false
    


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.setupViews()
        setListeners()

    }

    private fun FragmentDetailBinding.setupViews() {
        val articles = args.article
        detailAuthor.text = articles.author.toString()
        detailContent.text = articles.content.toString()
        detailDescription.text = articles.description.toString()
        detailTitle.text = articles.title.toString()
        Glide.with(root).load(articles.urlToImage).into(detailMedia)
    }

    fun setPopUpMenu(view: View) {
        val savedNews=args.article
        val popupMenu = PopupMenu(requireActivity(), view)
        popupMenu.menuInflater.inflate(R.menu.pop_up_menu, popupMenu.menu)
        popupMenu.gravity = Gravity.END
        val popupmenu = popupMenu.menu.findItem(R.id.popup_save)

        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.popup_save ->
                    isSaved = if (isSaved) {
                        detailViewModel.deleteNews(savedNews.toSavedNews())
                        popupmenu.setIcon(R.drawable.baseline_bookmark_border_24)
                        false
                    } else {
                        detailViewModel.addSaved(savedNews.toSavedNews())

                        popupmenu.setIcon(R.drawable.baseline_selected_save)
                        true
                    }

                R.id.popup_share ->
                    true
            }
            true
        }

        try {
            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            val menu = popup.get(popupMenu)
            menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                .invoke(menu, true)
        } catch (e: Exception) {

            e.printStackTrace()
        } finally {
            popupMenu.show()

        }
    }

     fun setListeners() {
        binding.detailReadMore.setOnClickListener {
            val uri = Uri.parse(args.article.url)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
         binding.moreButton.setOnClickListener {
             setPopUpMenu(it)
         }
         binding.btnBack.setOnClickListener {
             findNavController().popBackStack()


         }
    }
}


package com.example.dmiryz.ryzhov.movieinfo.app.fragments.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dmiryz.ryzhov.movieinfo.R


class FavoriteFragment : Fragment() {

    private lateinit var galleryViewModel: FavoriteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        galleryViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        super.onActivityCreated(savedInstanceState)
    }
}


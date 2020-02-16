package com.example.dmiryz.ryzhov.movieinfo.fragments.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.dmiryz.movie.fragments.settings.SettingsViewModel
import com.example.dmiryz.ryzhov.movieinfo.R


class SettingsFragment : Fragment() {

    private lateinit var toolsViewModel: SettingsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolsViewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)
    }
}
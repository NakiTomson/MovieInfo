package com.example.dmiryz.ryzhov.movieinfo.app.fragments.exit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dmiryz.ryzhov.movieinfo.R


class ExitFragment : Fragment() {

    private lateinit var shareViewModel: ExitViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_exit, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        shareViewModel = ViewModelProvider(this).get(ExitViewModel::class.java)
    }
}
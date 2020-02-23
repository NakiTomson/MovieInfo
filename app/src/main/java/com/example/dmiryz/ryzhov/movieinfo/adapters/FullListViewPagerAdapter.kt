package com.example.dmiryz.ryzhov.movieinfo.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dmiryz.ryzhov.movieinfo.fragments.full_movie.SectionFullMovieListFragmentArgs
import com.example.dmiryz.ryzhov.movieinfo.fragments.full_movie.full_movie.FullMovieFragment


class FullListViewPagerAdapter(
    fragmentActivity: FragmentActivity,
    val args: SectionFullMovieListFragmentArgs
): FragmentStateAdapter(fragmentActivity){


    override fun createFragment(position: Int): Fragment {
        val bundle = Bundle()
        bundle.putSerializable("ListMovieCategory", args.movieList)
        val fullMovieFragment = FullMovieFragment.newInstance(position)
        fullMovieFragment.arguments = bundle
        return fullMovieFragment
    }

    override fun getItemCount(): Int {
        return CARD_ITEM_SIZE
    }

    companion object {
        private const val CARD_ITEM_SIZE = 2
    }

}
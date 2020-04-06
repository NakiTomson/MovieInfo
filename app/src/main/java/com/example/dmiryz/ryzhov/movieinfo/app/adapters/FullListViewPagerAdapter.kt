package com.example.dmiryz.ryzhov.movieinfo.app.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dmiryz.ryzhov.movieinfo.app.fragments.full_movie_by_category.SectionFullMovieListFragmentArgs
import com.example.dmiryz.ryzhov.movieinfo.app.fragments.full_movie_by_category.popular_full.PopularFullMovieFragment
import com.example.dmiryz.ryzhov.movieinfo.app.fragments.full_movie_by_category.rated_full.RatedFullMovieFragment


class FullListViewPagerAdapter(fragmentActivity: FragmentActivity, val args: SectionFullMovieListFragmentArgs) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        val bundle = Bundle()
        bundle.putSerializable("ListMovieCategory", args.movieList)
        val fragment = when (position) {
            0 -> PopularFullMovieFragment.newInstance()
            1 -> RatedFullMovieFragment.newInstance()
            else -> throw Exception("Need new Fragment Instance")
        }
        fragment.arguments = bundle
        return fragment
    }

    override fun getItemCount(): Int {
        return CARD_ITEM_SIZE
    }

    companion object {
        private const val CARD_ITEM_SIZE = 2
    }

}
package com.example.dmiryz.ryzhov.movieinfo.app.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dmiryz.ryzhov.movieinfo.app.fragments.movie.category_movie_film.FilmsCategoryMovieFragment
import com.example.dmiryz.ryzhov.movieinfo.app.fragments.movie.category_movie_series.SeriesCategoryMovieFragment
import com.example.dmiryz.ryzhov.movieinfo.app.fragments.movie.movie_recomend.RecommendedMovieFragment
import java.lang.Exception

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> RecommendedMovieFragment.newInstance()
            1 -> FilmsCategoryMovieFragment.newInstance()
            2 -> SeriesCategoryMovieFragment.newInstance()
            else -> throw Exception("Need new Fragment Instance")
        }
    }

    override fun getItemCount(): Int {
        return CARD_ITEM_SIZE
    }

    companion object {
        private const val CARD_ITEM_SIZE = 3
    }
}
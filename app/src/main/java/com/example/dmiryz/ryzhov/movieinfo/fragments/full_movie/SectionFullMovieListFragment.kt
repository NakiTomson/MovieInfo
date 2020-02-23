package com.example.dmiryz.ryzhov.movieinfo.fragments.full_movie

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.dmiryz.ryzhov.movieinfo.R
import com.example.dmiryz.ryzhov.movieinfo.adapters.FullListViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.full_movie_list_fragment.*


class SectionFullMovieListFragment : Fragment() {

    val args: SectionFullMovieListFragmentArgs by navArgs()

    companion object {
        fun newInstance() = SectionFullMovieListFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.full_movie_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setData()
        TabLayoutMediator(tabLayoutFullMovie, viewPagerFullMovie,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                val values: Array<String> = context!!.resources.getStringArray(R.array.tabs2)
                tab.text = values[position]
            }).attach()

        viewPagerFullMovie.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {

            }
        })

    }

    private fun setData() {
        viewPagerFullMovie.adapter = createMovieListAdapter()
        viewPagerFullMovie.clipToPadding = false
        viewPagerFullMovie.clipChildren = false
        viewPagerFullMovie.offscreenPageLimit = 3
        viewPagerFullMovie.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - Math.abs(position)
            page.scaleY = (0.85f + r * 0.15f)
        }
        viewPagerFullMovie.setPageTransformer(compositePageTransformer)
    }

    private fun createMovieListAdapter(): FullListViewPagerAdapter? {
        return FullListViewPagerAdapter(context as FragmentActivity,args)
    }
}

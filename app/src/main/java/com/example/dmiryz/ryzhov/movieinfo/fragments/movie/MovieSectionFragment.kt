package com.example.dmiryz.ryzhov.movieinfo.fragments.movie


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2

import com.example.dmiryz.ryzhov.movieinfo.R
import com.example.dmiryz.ryzhov.movieinfo.adapters.ViewPagerAdapter
import com.example.dmiryz.ryzhov.movieinfo.utils.Configs
import com.example.dmiryz.ryzhov.movieinfo.utils.Configs.Companion.myPosition
import com.example.dmiryz.ryzhov.movieinfo.utils.Configs.Companion.stateOne
import com.example.dmiryz.ryzhov.movieinfo.utils.Configs.Companion.stateThree
import com.example.dmiryz.ryzhov.movieinfo.utils.Configs.Companion.stateTwo
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * A simple [Fragment] subclass.
 */
class MovieSectionFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setData()

        TabLayoutMediator(tabLayout, viewPager2,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                val values: Array<String> = context!!.resources.getStringArray(R.array.tabs)
                tab.text = values[position]
            }).attach()

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                myPosition = position
                when (position) {
                    0 -> activity?.findViewById<AppBarLayout>(R.id.appBarLayout)?.setExpanded(stateOne)
                    1 -> activity?.findViewById<AppBarLayout>(R.id.appBarLayout)?.setExpanded(stateTwo)
                    2 -> activity?.findViewById<AppBarLayout>(R.id.appBarLayout)?.setExpanded(stateThree)
                    else -> throw Exception("xmmm...")
                }
            }
        })
    }

    private fun setData() {
        setHasOptionsMenu(true)
        Configs.stateAppBarDetail = false

        viewPager2.adapter = createCardAdapter()
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.offscreenPageLimit = 3
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - Math.abs(position)
            page.scaleY = (0.85f + r * 0.15f)
        }
        viewPager2.setPageTransformer(compositePageTransformer)

        activity?.findViewById<ImageView>(R.id.expandedImage)?.setImageResource(R.drawable.poster_real_size)
        activity?.findViewById<FloatingActionButton>(R.id.fab)?.visibility = View.GONE
    }


    private fun createCardAdapter(): ViewPagerAdapter? {
        return ViewPagerAdapter(context as FragmentActivity)
    }
}

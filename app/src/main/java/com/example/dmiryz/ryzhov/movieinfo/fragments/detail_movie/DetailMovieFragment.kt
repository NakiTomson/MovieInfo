package com.example.dmiryz.ryzhov.movieinfo.fragments.detail_movie

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dmiryz.ryzhov.domain.models.MovieDetailEntity
import com.example.dmiryz.ryzhov.domain.models.MovieReviewEntity
import com.example.dmiryz.ryzhov.domain.models.MovieTraillerEntity
import com.example.dmiryz.ryzhov.movieinfo.R
import com.example.dmiryz.ryzhov.movieinfo.adapters.ReviewAdapter
import com.example.dmiryz.ryzhov.movieinfo.utils.Configs
import com.example.dmiryz.ryzhov.movieinfo.utils.Configs.Companion.API_YOUTUBE_KEY
import com.example.dmiryz.ryzhov.movieinfo.utils.Configs.Companion.stateAppBarExpandedFunction
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.description_movie.*
import kotlinx.android.synthetic.main.detail_movie_fragment.*
import kotlinx.android.synthetic.main.movie_info.*


class DetailMovieFragment : Fragment(), YouTubePlayer.OnInitializedListener {


    private lateinit var viewModelDetail: DetailMovieViewModel
    lateinit var reviewAdapter: ReviewAdapter
    var frag: YouTubePlayerSupportFragment? = null
    lateinit var video: String

    val args: DetailMovieFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        return inflater.inflate(R.layout.detail_movie_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
        setData()
        viewModelDetail.getDetailsMovie(args.id)
        viewModelDetail.getReviewMovie(args.id)
    }

    private fun initData() {
        viewModelDetail = ViewModelProviders.of(this).get(DetailMovieViewModel::class.java)
        activity?.findViewById<AppBarLayout>(R.id.appBarLayout)?.setExpanded(true)
        activity?.findViewById<FloatingActionButton>(R.id.fab)?.visibility = View.VISIBLE

        stateAppBarExpandedFunction = true


        reviewAdapter = ReviewAdapter()
        recyclerViewReviews.layoutManager = LinearLayoutManager(activity)
        recyclerViewReviews.setHasFixedSize(true)
        recyclerViewReviews.adapter = reviewAdapter
        frag = this.childFragmentManager.findFragmentById(R.id.youtube_fragment) as YouTubePlayerSupportFragment?

        name_movie.transitionName = args.titleMovie
        name_movie.text = args.titleMovie
        ratingBar.rating = args.voteAverage
        rating.text = "${args.voteAverage} /"
        vote_count.text = "${args.voteCount} votes"
        description.text = args.overview
        reasled_date.text = "Released: ${args.yearStart}"
        release_date.text = args.yearStart
    }

    private fun setData() {
        val moviePosterUri = args.posterPath
        val movieBackPosterUri = args.backdropPath

        poster_image.apply {
            transitionName = moviePosterUri
            Picasso.get()
                .load(moviePosterUri)
                .noFade()
                .into(this)
        }

        Picasso.get()
            .load(movieBackPosterUri)
            .placeholder(R.drawable.poster_real_size)
            .into(activity?.findViewById<ImageView>(R.id.expandedImage))


        viewModelDetail.movieDetail.observe(activity!!, Observer<MovieDetailEntity> {
            val hourse: Int = it.runtime / 60
            val minute: Int = it.runtime % 60;
            val time = "$hourse ч, $minute м"
            runtime_movie.text = time
            budget.text = "${it.budget} $"

            it.genreOne?.let {
                gender.text = it
                gender.visibility = View.VISIBLE
            }
            it.genreTwo?.let {
                gender2.text = it
                gender2.visibility = View.VISIBLE
            }
            it.genreThree?.let {
                gender3.text = it
                gender3.visibility = View.VISIBLE
            }
            it.genreFoure?.let {
                gender4.text = it
                gender4.visibility = View.VISIBLE
            }
            viewModelDetail.getTrailer(args.id)
        })

        viewModelDetail.movieRevies.observe(activity!!, Observer<List<MovieReviewEntity>> {
            reviewAdapter.setReviews(it)
        })

        viewModelDetail.movieTrailler.observe(activity!!, Observer<MovieTraillerEntity> {
            video = it.keyTrailer
            frag!!.initialize(API_YOUTUBE_KEY, this)
        })

    }


    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
        p1!!.cueVideo(video)
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
        if (p1!!.isUserRecoverableError) {
            Toast.makeText(context, "Error if $", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Error else", Toast.LENGTH_LONG).show()
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> activity?.findViewById<AppBarLayout>(R.id.appBarLayout)?.setExpanded(false)
        }
        return true
    }

}

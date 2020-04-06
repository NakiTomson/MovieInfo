package com.example.dmiryz.ryzhov.movieinfo.app.fragments.detail_movie

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.dmiryz.ryzhov.movieinfo.R
import com.example.dmiryz.ryzhov.movieinfo.app.adapters.*
import com.example.dmiryz.ryzhov.movieinfo.app.utils.Configs.Companion.API_YOUTUBE_KEY
import com.example.dmiryz.ryzhov.movieinfo.app.utils.Configs.Companion.stateAppBarExpandedFunction
import com.example.dmiryz.ryzhov.movieinfo.domain.models.*
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import  com.example.dmiryz.ryzhov.movieinfo.app.adapters.RecommendedMovieAdapter.Companion.StrategyBind
import kotlinx.android.synthetic.main.description_movie.*
import kotlinx.android.synthetic.main.detail_movie_fragment.*
import kotlinx.android.synthetic.main.movie_info.*


class DetailMovieFragment : Fragment(), YouTubePlayer.OnInitializedListener {


    private lateinit var viewModelDetail: DetailMovieViewModel
    var frag: YouTubePlayerSupportFragment? = null
    lateinit var reviewAdapter: ReviewAdapter
    lateinit var video: String
    lateinit var adapter:ImageSlideMovieAdapter
    lateinit var myCategoryAdapter: RecommendedMovieAdapter

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

        myCategoryAdapter.movieSelectedListener = object : RecommendedMovieAdapter.MovieSelectedListener {
            override fun onMovieSelected(movie: MovieEntity, imageView: ImageView, title: TextView) {
//                val extras = FragmentNavigatorExtras(
//                    imageView to movie.posterPath,
//                    title to  movie.title
//                )
//                val action = DetailMovieFragmentDirections.actionDetailMovieFragmentSelf(
//                    titleMovie = movie.title,
//                    posterPath = movie.posterPath,
//                    backdropPath = movie.posterBackDropPath,
//                    yearStart = movie.year,
//                    voteCount = movie.voteCount,
//                    voteAverage = movie.voteAverage.toFloat(),
//                    overview = movie.overview,
//                    id = movie.id
//                )
//                findNavController().navigate(action, extras)
            }
        }
    }

    private fun initData() {
        viewModelDetail = ViewModelProvider(this).get(DetailMovieViewModel::class.java)
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


        //ImageSet
        adapter = ImageSlideMovieAdapter()
        viewPagerImages.adapter = adapter
        viewPagerImages.clipToPadding = false
        viewPagerImages.clipChildren = false
        viewPagerImages.offscreenPageLimit = 3
        viewPagerImages.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - Math.abs(position)
            page.scaleY = (0.85f + r * 0.15f)
        }
        viewPagerImages.setPageTransformer(compositePageTransformer)

//        SimiliarMovie
        myCategoryAdapter = RecommendedMovieAdapter(StrategyBind.bindRetedMovie)
        recyclerSimiliarMovie.layoutManager = LinearLayoutManager(activity)
        recyclerSimiliarMovie.adapter = myCategoryAdapter
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
            .into(activity?.findViewById(R.id.expandedImage),object:Callback{
                override fun onSuccess() {
                        viewModelDetail.getDetailsMovie(args.id)
                        viewModelDetail.getReviewMovie(args.id)
                }
                override fun onError(e: Exception?) {}
            })

        viewModelDetail.movieDetail.observe(activity!!, Observer<MovieDetailEntity> {
            val hourse: Int = it.runtime / 60
            val minute: Int = it.runtime % 60;
            val time = "$hourse ч, $minute м"
            runtime_movie.text = time
            budgetMovie.text = "${it.budget} $"
            revenueMoneyMovie.text = "${it.revenue} $"
            buttonHomepage.text = it.homepage
            funSetInvisibleEntety(it)
            viewModelDetail.getTrailer(args.id)
            viewModelDetail.getImageMovieById(args.id)
            viewModelDetail.getSimilarMovieById(args.id)
        })

        viewModelDetail.movieRevies.observe(activity!!, Observer<List<MovieReviewEntity>> {
            reviewAdapter.setReviews(it)
        })

        viewModelDetail.movieTrailler.observe(activity!!, Observer<MovieTraillerEntity> {
            video = it.keyTrailer
            frag?.let { it.initialize(API_YOUTUBE_KEY, this) }
        })
        viewModelDetail.movieImages.observe(activity!!, Observer {
            adapter.addMovies(it)
        })
        viewModelDetail.similarMovie.observe(activity!!, Observer {
            myCategoryAdapter.addMoviesCategory(MovieCategoryEntity("Похожие",it,""))
        })
    }

    private fun funSetInvisibleEntety(it: MovieDetailEntity) {
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
        it.firstCountryDevelop?.let {
            textViewFirstCountry.text = it
            textViewFirstCountry.visibility = View.VISIBLE
        }

        it.secondCountryDevelop?.let {
            textViewSecondCountry.text = it
            textViewSecondCountry.visibility = View.VISIBLE
        }
        it.thirdCountryDevelop?.let {
            textViewThirdCountry.text = it
            textViewThirdCountry.visibility = View.VISIBLE
        }

        it.firstCompanyDevelop?.let {
            textViewFirstCompany.text = it
            textViewFirstCompany.visibility = View.VISIBLE
        }

        it.secondCompanyDevelop?.let {
            textViewSecondCompany.text = it
            textViewSecondCompany.visibility = View.VISIBLE
        }

        it.thirdCompanyDevelop?.let {
            textViewThirdCompany.text = it
            textViewThirdCompany.visibility = View.VISIBLE
        }
        it.fourthCompanyDevelop?.let {
            textViewFourthCompany.text = it
            textViewFourthCompany.visibility = View.VISIBLE
        }
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
        return super.onOptionsItemSelected(item)
    }

}

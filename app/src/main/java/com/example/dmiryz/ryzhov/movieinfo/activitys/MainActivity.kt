package com.example.dmiryz.ryzhov.movieinfo.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.dmiryz.ryzhov.movieinfo.R
import com.example.dmiryz.ryzhov.movieinfo.utils.AppBarStateChangeListener
import com.example.dmiryz.ryzhov.movieinfo.utils.Configs
import com.example.dmiryz.ryzhov.movieinfo.utils.Configs.Companion.stateAppBarExpandedFunction
import com.example.dmiryz.ryzhov.movieinfo.utils.Configs.Companion.stateFive
import com.example.dmiryz.ryzhov.movieinfo.utils.Configs.Companion.stateFoure
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.defaul_toolbar.*

class MainActivity : AppCompatActivity() {

    lateinit var decorView: View
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        decorView = window.decorView
        decorView.setOnSystemUiVisibilityChangeListener {
            if (it == 0) {
                decorView.systemUiVisibility = hideSystemBar()
            }
        }


        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        val defaultToolbar = layoutInflater.inflate(
            R.layout.defaul_toolbar,
            appBarLayout,
            false
        ) as CollapsingToolbarLayout
        val allMovieToolbar = layoutInflater.inflate(
            R.layout.all_movie_toolbar,
            appBarLayout,
            false
        ) as CollapsingToolbarLayout

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_movie,
                R.id.nav_favorite_movie,
                R.id.nav_settings,
                R.id.nav_exit
            ), drawerLayout
        )

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
//                R.id.SectionfullMovieListFragment -> {
//                    appBarLayout.removeAllViews()
//                    appBarLayout.addView(defaultToolbar)
//                    setSupportActionBar(allMovieToolbar.findViewById(R.id.all_movies_toolbar))
//                }
                else -> {
                    appBarLayout.removeAllViews()
                    appBarLayout.addView(defaultToolbar)
                    setSupportActionBar(defaultToolbar.findViewById(R.id.toolbar_default))
                }
            }
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)
            changeConfigurationAppBar()
        }
    }

    private fun changeConfigurationAppBar() {
        findViewById<AppBarLayout>(R.id.appBarLayout)?.addOnOffsetChangedListener(object :
            AppBarStateChangeListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout, state: State) {
                if (stateAppBarExpandedFunction) return
                when (Configs.myPositionOnViewPagersFragments) {
                    0 -> Configs.stateOne = getCurrentStateAppBar(sate = state.name)
                    1 -> Configs.stateTwo = getCurrentStateAppBar(sate = state.name)
                    2 -> Configs.stateThree = getCurrentStateAppBar(sate = state.name)
                    3 -> {
                        Configs.stateFoure = getCurrentStateAppBar(sate = state.name)
                        Log.i("TAGStateFoureAppBar", stateFoure.toString())
                    }
                    4 -> {
                        Configs.stateFive = getCurrentStateAppBar(sate = state.name)
                        Log.i("TAGStateFiveAppBar", stateFive.toString())
                    }
                    else -> throw Exception("it dosent work")
                }
            }
        })
    }

    fun getCurrentStateAppBar(sate: String): Boolean {
        return when (sate) {
            "COLLAPSED" -> false
            else -> true
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            decorView.systemUiVisibility = hideSystemBar()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun hideSystemBar(): Int {
        return (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }
}

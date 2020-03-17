package com.example.dmiryz.ryzhov.movieinfo.app

import android.app.Application
import com.example.dmiryz.ryzhov.movieinfo.app.di.AppComponent
import com.example.dmiryz.ryzhov.movieinfo.app.di.AppModule
import com.example.dmiryz.ryzhov.movieinfo.app.di.DaggerAppComponent

class App: Application() {

    companion object{
        lateinit var appComponent:AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(app = this@App)).build()
    }
}


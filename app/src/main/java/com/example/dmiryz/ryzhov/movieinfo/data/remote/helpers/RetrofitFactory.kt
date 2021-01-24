package com.example.dmiryz.ryzhov.movieinfo.data.remote.helpers

import com.example.dmiryz.ryzhov.movieinfo.data.remote.services.MovieServices
import com.example.dmiryz.ryzhov.movieinfo.app.utils.Configs
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

//    companion object{
//        private fun createRetrofit(): Retrofit {
//            return Retrofit.Builder()
//                .baseUrl(Configs.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(createOkHttpClient())
//                .addCallAdapterFactory(CoroutineCallAdapterFactory())
//                .build()
//        }
//
//        private fun createOkHttpClient(): OkHttpClient {
//            val httpClient = OkHttpClient.Builder()
//            httpClient.addInterceptor(object : Interceptor {
//                override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
//                    val original = chain.request()
//                    val originalHttpUrl = original.url
//                    val url = originalHttpUrl.newBuilder()
//                        .addQueryParameter("api_key", Configs.API_KEY)
//                        .build()
//
//                    val requestBuilder = original.newBuilder()
//                        .url(url)
//                    val request = requestBuilder.build()
//                    return chain.proceed(request)
//                }
//            })
//
//            val logging =  HttpLoggingInterceptor()
//            logging.level = HttpLoggingInterceptor.Level.BODY
//            httpClient.addInterceptor(logging)
//            return httpClient.build()
//        }
//
//        fun getMovieService() = RetrofitFactory.createRetrofit().create(MovieServices::class.java)
//    }
}
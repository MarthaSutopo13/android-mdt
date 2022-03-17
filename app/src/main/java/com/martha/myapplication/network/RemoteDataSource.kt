package com.martha.myapplication.network

import android.content.Context
import android.util.Log
import com.martha.myapplication.BuildConfig
import com.martha.myapplication.util.DataPreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RemoteDataSource (context: Context){
    val headerInterceptor = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            var prefs = DataPreferences(context)
            var userPreference = runBlocking { prefs.getToken.first() }
            request = request.newBuilder()
                .addHeader("Authorization", "${userPreference}")
                .build()

            val response = chain.proceed(request)
            return response
        }
    }

    fun <Api> buildApi(
        api: Class<Api>,
    ): Api {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(OkHttpClient.Builder().also { client ->
                client.callTimeout(60, TimeUnit.SECONDS)
                client.addInterceptor(headerInterceptor)
                if (BuildConfig.DEBUG){
                    val logging = HttpLoggingInterceptor()
                    Log.e("logging : ",logging.toString())
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(logging)
                }
            }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}
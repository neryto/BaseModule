package com.nery.bustos.basemodule.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {

    private const val BASE_URL = "https://myurlapi" //configure your api url here
    private val client = OkHttpClient.Builder().build()
    fun <T> createApi(api: Class<T>, baseUrl: String = BASE_URL): T =
        Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client).build().create(api)

    const val AP1K3Y = "myapikey" //configure your api key here

}
package com.azad.productcatalogappairawatrf.data.remotedata

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitAPIClient {
    private var retrofitAPIClient: ApiService? = null
    private var BASE_URL = "https://dummyjson.com/"

    fun getInstance(): ApiService? {
        if (retrofitAPIClient == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofitAPIClient = retrofit.create(ApiService::class.java)
        }
        return retrofitAPIClient
    }
}
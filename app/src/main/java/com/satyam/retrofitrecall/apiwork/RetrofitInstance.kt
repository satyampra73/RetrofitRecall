package com.satyam.retrofitrecall.apiwork

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{
        val BASEURL = "https://jsonplaceholder.typicode.com"
        fun getRetrofitInstance(): Retrofit{
            return Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()

        }
    }
}
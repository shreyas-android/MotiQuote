package com.androidai.learning.moti.quote.remoteservice

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface QuoteService {

    @GET("/quotes/random")
    fun getRandomQuote() : Call<ResponseBody> // Replace with actual endpoint and response data structure
}
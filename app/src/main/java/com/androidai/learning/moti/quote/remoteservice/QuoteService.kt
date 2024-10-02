package com.androidai.learning.moti.quote.remoteservice

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface QuoteService {

    @GET("/v1/quotes")
    fun getRandomQuote( @Query("category") category:String, @Header("X-Api-Key") apiKey:String) : Call<ResponseBody> // Replace with actual endpoint and response data structure
}
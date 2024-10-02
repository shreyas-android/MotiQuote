package com.androidai.learning.moti.quote.repository.remote

import com.androidai.learning.moti.quote.repository.QuoteRepository
import com.androidai.learning.moti.quote.data.domain.Quote
import com.androidai.learning.moti.quote.data.response.NinjaQuoteResponse
import com.androidai.learning.moti.quote.data.response.QuoteResponse
import com.androidai.learning.moti.quote.remoteservice.QuoteService
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.json.JSONArray
import retrofit2.Retrofit
import retrofit2.awaitResponse

class RemoteQuoteRepositoryImpl : QuoteRepository {

    private val retrofit =
        Retrofit.Builder().baseUrl("https://api.api-ninjas.com") // Replace with actual API URL
            .build()

    private val quoteService = retrofit.create(QuoteService::class.java)

    override suspend fun getRandomQuote(category:String, apiKey:String) : Quote {
        val response =
            quoteService.getRandomQuote(category, apiKey).awaitResponse() // Replace with your API endpoint call


        if(response.isSuccessful) { // Check for HTTP status code in success range
            val responseBody =
                response.body()?.string() // Assuming successful response and data parsing
            println("RESPONSE - $responseBody")
            val jsonArray = JSONArray(responseBody)
            val json = jsonArray.getJSONObject(0)
            val quoteResponse = if(json != null) {
                Json.decodeFromString<NinjaQuoteResponse>(json.toString())
            } else {
                null
            }
            return Quote(
                quoteResponse?.quote
                    ?: "", quoteResponse?.author
                    ?: "", quoteResponse?.category ?: "")
        } else {
            val errorMsg = "Network error: ${response.code()}"
            throw Exception(errorMsg)
        }
    }
}






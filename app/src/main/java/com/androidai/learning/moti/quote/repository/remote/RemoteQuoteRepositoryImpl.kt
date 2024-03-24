package com.androidai.learning.moti.quote.repository.remote

import com.androidai.learning.moti.quote.repository.QuoteRepository
import com.androidai.learning.moti.quote.data.domain.Quote
import com.androidai.learning.moti.quote.data.response.QuoteResponse
import com.androidai.learning.moti.quote.remoteservice.QuoteService
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.json.JSONArray
import retrofit2.Retrofit
import retrofit2.awaitResponse

class RemoteQuoteRepositoryImpl : QuoteRepository {

    private val retrofit =
        Retrofit.Builder().baseUrl("https://api.quotable.io/") // Replace with actual API URL
            .build()

    private val quoteService = retrofit.create(QuoteService::class.java)

    override suspend fun getRandomQuote() : Quote {
        val response =
            quoteService.getRandomQuote().awaitResponse() // Replace with your API endpoint call

        if(response.isSuccessful) { // Check for HTTP status code in success range
            val responseBody =
                response.body()?.string() // Assuming successful response and data parsing
            val jsonArray = JSONArray(responseBody)
            val json = jsonArray.getJSONObject(0)
            val quoteResponse = if(json != null) {
                Json.decodeFromString<QuoteResponse>(json.toString())
            } else {
                null
            }
            return Quote(
                quoteResponse?.content
                    ?: "", quoteResponse?.author
                    ?: "")
        } else {
            val errorMsg = "Network error: ${response.code()}"
            throw Exception(errorMsg)
        }
    }
}






package com.androidai.learning.moti.quote.repository

import com.androidai.learning.moti.quote.data.domain.Quote

interface QuoteRepository {
    suspend fun getRandomQuote(category:String, apiKey:String): Quote
}

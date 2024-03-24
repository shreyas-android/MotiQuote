package com.androidai.learning.moti.quote.repository.local

import com.androidai.learning.moti.quote.repository.QuoteRepository
import com.androidai.learning.moti.quote.data.domain.Quote

class LocalQuoteRepositoryImpl : QuoteRepository {

    private val quotes = listOf( // Replace with actual database interaction
        Quote("The best and most beautiful things in the world cannot be seen or even touched - they must be felt with the heart. - Helen Keller"),
        Quote("Hold fast to dreams, for if dreams die, life is like a bird with broken wings that cannot fly. - Langston Hughes")
    )

    override suspend fun getRandomQuote(): Quote {
        val randomIndex = (quotes.indices).random()
        return quotes[randomIndex]
    }
}

package com.androidai.learning.moti.quote.data.response

import kotlinx.serialization.Serializable

@Serializable
data class QuoteResponse(val _id: String,
                         val content: String,
                         val author: String,
                         val tags: List<String>,
                         val authorSlug: String,
                         val length: Int,
                         val dateAdded: String,
                         val dateModified: String) // Replace with actual response data structure
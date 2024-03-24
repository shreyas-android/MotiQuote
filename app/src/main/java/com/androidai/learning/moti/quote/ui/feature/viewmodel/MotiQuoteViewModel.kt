package com.androidai.learning.moti.quote.ui.feature.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidai.learning.moti.quote.data.domain.Quote
import com.androidai.learning.moti.quote.repository.QuoteRepository
import kotlinx.coroutines.launch

class MotiQuoteViewModel(private val quoteRepository: QuoteRepository) : ViewModel() {

    private val _currentQuote = MutableLiveData<Quote>()
    val currentQuote: LiveData<Quote>
        get() = _currentQuote

    fun fetchQuote() {
        viewModelScope.launch {
            try {
                val quote = quoteRepository.getRandomQuote() // Replace with logic to fetch quote
                _currentQuote.value = quote
            }catch(e:Exception){
                Log.e("MotiQuoteViewModel", "Error fetching quote", e)
            }
        }
    }
}

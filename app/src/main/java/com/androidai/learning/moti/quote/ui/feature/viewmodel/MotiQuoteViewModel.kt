package com.androidai.learning.moti.quote.ui.feature.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidai.learning.moti.quote.data.domain.Quote
import com.androidai.learning.moti.quote.repository.QuoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MotiQuoteViewModel(private val quoteRepository : QuoteRepository) : ViewModel() {

    private val _currentQuote = MutableLiveData<Quote>()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing : StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    val currentQuote : LiveData<Quote>
        get() = _currentQuote

    suspend fun fetchQuote() {
        try {
            val quote = quoteRepository.getRandomQuote() // Replace with logic to fetch quote
            _currentQuote.value = quote
        } catch(e : Exception) {
            Log.e("MotiQuoteViewModel", "Error fetching quote", e)
        }
    }

    fun refresh() { // Don't set _isRefreshing to true here as this will be called on init,
        //  the pull to refresh api will handle setting _isRefreshing to true
        viewModelScope.launch {
            _isRefreshing.emit(true)
            fetchQuote()
            _isRefreshing.emit(false)
        }
    }
}

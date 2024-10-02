package com.androidai.learning.moti.quote.ui.feature.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidai.learning.moti.quote.data.domain.Quote
import com.androidai.learning.moti.quote.repository.QuoteRepository
import com.androidai.learning.moti.quote.utils.RemoteUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MotiQuoteViewModel(private val apiKey:String, private val quoteRepository : QuoteRepository) : ViewModel() {

    private val _currentQuote = MutableLiveData<Quote>()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing : StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    val currentQuote : LiveData<Quote>
        get() = _currentQuote

    suspend fun fetchQuote() {
        try {
            val category = RemoteUtils.getRandomCategory()
            val quote = quoteRepository.getRandomQuote(category, apiKey) // Replace with logic to fetch quote
            _currentQuote.value = quote
            Log.d("MotiQuoteViewModel", "SUCCESS fetching quote = ${quote}")
        } catch(e : Exception) {
            Log.d("MotiQuoteViewModel", "Error fetching quote = ${e.message}")
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

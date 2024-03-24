package com.androidai.learning.moti.quote.ui.feature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidai.learning.moti.quote.repository.QuoteRepository

class MotiQuoteViewModelFactory(private val repository: QuoteRepository) : ViewModelProvider.Factory {

  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(MotiQuoteViewModel::class.java)) {
      @Suppress("unchecked_cast")
      return MotiQuoteViewModel(repository) as T
    }
    throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
  }
}
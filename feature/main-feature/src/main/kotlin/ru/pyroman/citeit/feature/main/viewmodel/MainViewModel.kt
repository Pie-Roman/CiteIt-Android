package ru.pyroman.citeit.feature.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.pyroman.citeit.domain.quote.repository.QuoteRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: QuoteRepository,
) : ViewModel() {

    val quoteList = repository.quoteList

    fun fetchQuoteList() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            repository.fetchQuoteList()
        }
    }

    fun removeLastQuote() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            repository.removeLastQuote()
        }
    }
}
package ru.pyroman.citeit.domain.quote.repository

import kotlinx.coroutines.flow.StateFlow
import ru.pyroman.citeit.domain.quote.model.Quote

interface QuoteRepository {

    val quoteList: StateFlow<List<Quote>>

    suspend fun getRandomQuote(): Quote

    suspend fun fetchQuoteList()

    suspend fun removeLastQuote()
}
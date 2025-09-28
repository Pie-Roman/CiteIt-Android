package ru.pyroman.citeit.domain.quote.repository

import ru.pyroman.citeit.domain.quote.model.Quote

interface QuoteRepository {

    suspend fun getRandomQuote(): Quote
}
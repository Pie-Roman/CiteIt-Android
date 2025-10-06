package ru.pyroman.citeit.data.quote.network.repository

import ru.pyroman.citeit.data.quote.network.datasource.QuoteNetworkDataSource
import ru.pyroman.citeit.data.quote.network.mapper.QuoteNetworkMapper
import ru.pyroman.citeit.domain.quote.model.Quote
import javax.inject.Inject

internal class QuoteNetworkRepository @Inject constructor(
    private val dataSource: QuoteNetworkDataSource,
    private val mapper: QuoteNetworkMapper,
) {

    suspend fun getRandomQuote(): Quote {
        val quoteDto = dataSource.getRandomQuote()
        return mapper.map(quoteDto)
    }

    suspend fun getQuoteList(): List<Quote> {
        val quoteListDto = dataSource.getQuoteList()
        return quoteListDto.map { quoteDto ->
            mapper.map(quoteDto)
        }
    }
}
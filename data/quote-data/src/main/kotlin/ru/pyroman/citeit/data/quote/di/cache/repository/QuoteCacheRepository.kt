package ru.pyroman.citeit.data.quote.di.cache.repository

import ru.pyroman.citeit.data.quote.di.cache.database.QuoteDao
import ru.pyroman.citeit.data.quote.di.cache.datastore.QuoteDataStore
import ru.pyroman.citeit.data.quote.di.cache.mapper.QuoteCacheMapper
import ru.pyroman.citeit.domain.quote.model.Quote
import javax.inject.Inject

internal class QuoteCacheRepository @Inject constructor(
    private val dao: QuoteDao,
    private val mapper: QuoteCacheMapper,
    private val dataStore: QuoteDataStore,
) {

    suspend fun getRandomQuote(): Quote {
        return dataStore.getRandomQuote()
    }

    suspend fun setRandomQuote(quote: Quote) {
        return dataStore.setRandomQuote(quote)
    }

    suspend fun setQuoteList(quoteList: List<Quote>) {
        val quoteListDto = quoteList.map { quote ->
            mapper.map(quote)
        }
        dao.insertQuoteList(quoteListDto)
    }

    suspend fun getQuoteList(): List<Quote> {
        val quoteListDto = dao.getQuoteList()
        return quoteListDto.map { quoteDto ->
            mapper.map(quoteDto)
        }
    }
}
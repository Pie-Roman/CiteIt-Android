package ru.pyroman.citeit.data.quote.repository

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import ru.pyroman.citeit.data.quote.di.cache.repository.QuoteCacheRepository
import ru.pyroman.citeit.data.quote.network.repository.QuoteNetworkRepository
import ru.pyroman.citeit.domain.quote.model.Quote
import ru.pyroman.citeit.domain.quote.repository.QuoteRepository
import javax.inject.Inject

internal class QuoteRepositoryImpl @Inject constructor(
    private val networkRepository: QuoteNetworkRepository,
    private val cacheRepository: QuoteCacheRepository,
) : QuoteRepository {

    private val _quoteList = MutableStateFlow<List<Quote>>(emptyList())
    override val quoteList = _quoteList.asStateFlow()

    override suspend fun getRandomQuote(): Quote {
        return try {
            networkRepository.getRandomQuote().apply {
                coroutineScope {
                    cacheRepository.setRandomQuote(this@apply)
                }
            }
        } catch (exception: HttpException) {
            cacheRepository.getRandomQuote()
        }
    }

    override suspend fun fetchQuoteList() {
        val quoteList = try {
            networkRepository.getQuoteList().apply {
                coroutineScope {
                    launch {
                        cacheRepository.setQuoteList(this@apply)
                    }
                }
            }
        } catch (exception: HttpException) {
            cacheRepository.getQuoteList()
        }

        _quoteList.emit(quoteList)
    }

    override suspend fun removeLastQuote() {
        val newQuoteList = quoteList.value.drop(1)
        _quoteList.emit(newQuoteList)
    }
}
package ru.pyroman.citeit.data.quote.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.pyroman.citeit.data.quote.network.repository.QuoteNetworkRepository
import ru.pyroman.citeit.domain.quote.model.Quote
import ru.pyroman.citeit.domain.quote.repository.QuoteRepository
import javax.inject.Inject

internal class QuoteRepositoryImpl @Inject constructor(
    private val networkRepository: QuoteNetworkRepository,
) : QuoteRepository {

    private val _quoteList = MutableStateFlow<List<Quote>>(emptyList())
    override val quoteList = _quoteList.asStateFlow()

    override suspend fun getRandomQuote(): Quote {
        return networkRepository.getRandomQuote()
    }

    override suspend fun fetchQuoteList() {
        _quoteList.emit(networkRepository.getQuoteList())
    }

    override suspend fun removeLastQuote() {
        val newQuoteList = quoteList.value.drop(1)
        _quoteList.emit(newQuoteList)
    }
}
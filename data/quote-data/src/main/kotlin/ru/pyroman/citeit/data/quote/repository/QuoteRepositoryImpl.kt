package ru.pyroman.citeit.data.quote.repository

import ru.pyroman.citeit.data.quote.network.repository.QuoteNetworkRepository
import ru.pyroman.citeit.domain.quote.model.Quote
import ru.pyroman.citeit.domain.quote.repository.QuoteRepository
import javax.inject.Inject

internal class QuoteRepositoryImpl @Inject constructor(
    private val networkRepository: QuoteNetworkRepository,
) : QuoteRepository {

    override suspend fun getRandomQuote(): Quote {
        return networkRepository.getRandomQuote()
    }
}
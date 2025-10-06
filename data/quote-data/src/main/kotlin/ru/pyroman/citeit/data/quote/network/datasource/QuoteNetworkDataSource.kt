package ru.pyroman.citeit.data.quote.network.datasource

import ru.pyroman.citeit.data.quote.network.api.QuoteNetworkApi
import ru.pyroman.citeit.data.quote.network.dto.QuoteNetworkDto
import javax.inject.Inject

internal class QuoteNetworkDataSource @Inject constructor(
    private val api: QuoteNetworkApi,
) {

    suspend fun getRandomQuote(): QuoteNetworkDto {
        return api.getRandomQuote().first()
    }

    suspend fun getQuoteList(): List<QuoteNetworkDto> {
        return api.getQuoteList()
    }
}
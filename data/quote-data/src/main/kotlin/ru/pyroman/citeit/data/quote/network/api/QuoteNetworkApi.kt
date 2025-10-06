package ru.pyroman.citeit.data.quote.network.api

import retrofit2.http.GET
import ru.pyroman.citeit.data.common.RetrofitBuilder
import ru.pyroman.citeit.data.quote.network.dto.QuoteNetworkDto

internal interface QuoteNetworkApi {

    @GET("random")
    suspend fun getRandomQuote(): List<QuoteNetworkDto>

    @GET("quotes")
    suspend fun getQuoteList(): List<QuoteNetworkDto>

    companion object {

        fun build(): QuoteNetworkApi = RetrofitBuilder()
            .build()
            .create(QuoteNetworkApi::class.java)
    }
}
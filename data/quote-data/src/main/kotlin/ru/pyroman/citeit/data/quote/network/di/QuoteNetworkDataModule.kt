package ru.pyroman.citeit.data.quote.network.di

import dagger.Module
import dagger.Provides
import ru.pyroman.citeit.data.quote.network.api.QuoteNetworkApi

@Module
interface QuoteNetworkDataModule {

    companion object {

        @Provides
        internal fun provideQuoteNetworkApi(): QuoteNetworkApi {
            return QuoteNetworkApi.build()
        }
    }
}
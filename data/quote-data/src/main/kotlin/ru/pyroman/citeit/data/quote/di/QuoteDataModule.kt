package ru.pyroman.citeit.data.quote.di

import dagger.Binds
import dagger.Module
import ru.pyroman.citeit.data.quote.network.di.QuoteNetworkDataModule
import ru.pyroman.citeit.data.quote.repository.QuoteRepositoryImpl
import ru.pyroman.citeit.domain.quote.repository.QuoteRepository

@Module(includes = [
    QuoteNetworkDataModule::class,
])
abstract class QuoteDataModule {

    @Binds
    internal abstract fun bindQuoteRepository(
        impl: QuoteRepositoryImpl,
    ): QuoteRepository
}
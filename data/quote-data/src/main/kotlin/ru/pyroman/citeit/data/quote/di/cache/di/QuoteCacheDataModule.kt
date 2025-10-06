package ru.pyroman.citeit.data.quote.di.cache.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import ru.pyroman.citeit.data.quote.di.cache.database.QuoteDao
import ru.pyroman.citeit.data.quote.di.cache.database.QuoteDatabase

@Module
interface QuoteCacheDataModule {

    companion object {

        @Provides
        internal fun provideQuoteDatabase(
            context: Context
        ): QuoteDatabase {
            return QuoteDatabase.build(
                context = context
            )
        }

        @Provides
        internal fun provideQuoteDao(
            database: QuoteDatabase,
        ): QuoteDao {
            return database.dao()
        }

        @Provides
        internal fun providePreferencesDataStore(
            context: Context,
        ): DataStore<Preferences> {
            return PreferenceDataStoreFactory.create(
                produceFile = { context.preferencesDataStoreFile("quote_prefs") }
            )
        }
    }
}
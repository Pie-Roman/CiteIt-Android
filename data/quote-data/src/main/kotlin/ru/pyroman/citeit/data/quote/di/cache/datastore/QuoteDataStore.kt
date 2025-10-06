package ru.pyroman.citeit.data.quote.di.cache.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import ru.pyroman.citeit.domain.quote.model.Quote
import ru.pyroman.citeit.domain.quote.model.QuoteColor
import javax.inject.Inject

internal class QuoteDataStore @Inject constructor(
    private val preferencesDataStore: DataStore<Preferences>,
) {

    private val gson: Gson = Gson()

    suspend fun setRandomQuote(quote: Quote) {
        val json = gson.toJson(quote)
        preferencesDataStore.edit { prefs ->
            prefs[RANDOM_QUOTE_KEY] = json
        }
    }

    suspend fun getRandomQuote(): Quote {
        return preferencesDataStore.data.map { prefs ->
            val json = prefs[RANDOM_QUOTE_KEY] ?: return@map null
            gson.fromJson(json, Quote::class.java)
        }.firstOrNull() ?: fallback
    }

    companion object {
        private val RANDOM_QUOTE_KEY = stringPreferencesKey("random_quote")
        private val fallback = Quote(
            id = "0",
            text = "Life reflects your own thoughts back to you",
            author = "Napoleon Hill",
            color = QuoteColor.random(),
        )
    }
}
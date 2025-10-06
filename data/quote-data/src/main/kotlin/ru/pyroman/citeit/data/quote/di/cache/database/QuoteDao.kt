package ru.pyroman.citeit.data.quote.di.cache.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.pyroman.citeit.data.quote.di.cache.dto.QuoteCacheDto

@Dao
internal interface QuoteDao {

    @Query("SELECT * FROM quote_table")
    suspend fun getQuoteList(): List<QuoteCacheDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuoteList(quoteList: List<QuoteCacheDto>)
}
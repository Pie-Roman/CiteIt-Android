package ru.pyroman.citeit.data.quote.di.cache.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.pyroman.citeit.data.quote.di.cache.dto.QuoteCacheDto

@Database(entities = [QuoteCacheDto::class], version = 1, exportSchema = false)
internal abstract class QuoteDatabase : RoomDatabase() {

    abstract fun dao(): QuoteDao

    companion object {
        fun build(
            context: Context,
        ) = Room
            .databaseBuilder(
                context = context,
                klass = QuoteDatabase::class.java,
                name = "quote_database",
            )
            .fallbackToDestructiveMigration()
            .build()
    }
}
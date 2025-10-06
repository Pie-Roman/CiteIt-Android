package ru.pyroman.citeit.data.quote.di.cache.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("quote_table")
data class QuoteCacheDto(
    @PrimaryKey val id: String,
    val text: String,
    val author: String,
)
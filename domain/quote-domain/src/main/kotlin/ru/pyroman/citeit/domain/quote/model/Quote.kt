package ru.pyroman.citeit.domain.quote.model

data class Quote(
    val id: String,
    val text: String,
    val author: String,
    val color: QuoteColor,
)
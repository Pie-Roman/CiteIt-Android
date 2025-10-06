package ru.pyroman.citeit.domain.quote.model

import kotlin.random.Random

data class QuoteColor(
    val red: Float,
    val green: Float,
    val blue: Float,
) {

    companion object {

        fun random(): QuoteColor {
            fun rand() = (Random.nextFloat() * (0.9f - 0.4f) + 0.4f - 0.1f).coerceIn(0f, 1f)
            return QuoteColor(rand(), rand(), rand())
        }
    }
}
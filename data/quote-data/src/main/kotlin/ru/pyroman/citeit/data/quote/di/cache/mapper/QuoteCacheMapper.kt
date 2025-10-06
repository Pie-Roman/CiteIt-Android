package ru.pyroman.citeit.data.quote.di.cache.mapper

import ru.pyroman.citeit.data.quote.di.cache.dto.QuoteCacheDto
import ru.pyroman.citeit.domain.quote.model.Quote
import ru.pyroman.citeit.domain.quote.model.QuoteColor
import javax.inject.Inject

internal class QuoteCacheMapper @Inject constructor() {

    fun map(dto: QuoteCacheDto): Quote {
        val id = dto.id
        val text = dto.text
        val author = dto.author
        val color = QuoteColor.random()

        return Quote(
            id = id,
            text = text,
            author = author,
            color = color,
        )
    }

    fun map(model: Quote): QuoteCacheDto {
        val id = model.id
        val text = model.text
        val author = model.author

        return QuoteCacheDto(
            id = id,
            text = text,
            author = author,
        )
    }
}
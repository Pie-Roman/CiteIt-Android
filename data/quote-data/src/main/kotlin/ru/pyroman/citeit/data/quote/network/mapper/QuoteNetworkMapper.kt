package ru.pyroman.citeit.data.quote.network.mapper

import ru.pyroman.citeit.data.quote.network.dto.QuoteNetworkDto
import ru.pyroman.citeit.domain.quote.model.Quote
import javax.inject.Inject

internal class QuoteNetworkMapper @Inject constructor() {

    fun map(dto: QuoteNetworkDto): Quote {
        val quote = requireNotNull(dto.text)
        val author = dto.author.orEmpty()
        val id = "${quote}_${author}"

        return Quote(
            id = id,
            text = quote,
            author = author,
        )
    }
}
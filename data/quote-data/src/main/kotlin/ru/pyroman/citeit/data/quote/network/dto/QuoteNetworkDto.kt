package ru.pyroman.citeit.data.quote.network.dto

import com.google.gson.annotations.SerializedName

internal data class QuoteNetworkDto(
    @SerializedName("q") val text: String?,
    @SerializedName("a") val author: String?,
)
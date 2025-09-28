package ru.pyroman.citeit.feature.start.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.pyroman.citeit.domain.quote.model.Quote
import ru.pyroman.citeit.domain.quote.repository.QuoteRepository
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val repository: QuoteRepository,
) : ViewModel() {

    private val _randomQuoteText = MutableStateFlow("")
    val randomQuoteText = _randomQuoteText.asStateFlow()

    private val _randomQuoteAuthor = MutableStateFlow("")
    val randomQuoteAuthor = _randomQuoteAuthor.asStateFlow()

    private val _showContinueButton = MutableStateFlow(false)
    val showContinueButton = _showContinueButton.asStateFlow()

    fun fetchRandomQuote() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            val quote = repository.getRandomQuote()
            val quoteText = quote.text
            val quoteAuthor = quote.author

            val quoteTextPlaceholder = buildString(quoteText.length) {
                for (letter in quoteText) {
                    when (letter) {
                        ' ', '\n' -> append(letter)
                        else -> append('\u00A0')
                    }
                }
            }

            _randomQuoteText.emit(quoteTextPlaceholder)

            val quoteAuthorPlaceholder = buildString(quoteAuthor.length) {
                for (letter in quoteAuthor) {
                    when (letter) {
                        ' ', '\n' -> append(letter)
                        else -> append('\u00A0')
                    }
                }
            }

            _randomQuoteAuthor.emit(quoteAuthorPlaceholder)

            typingAnimation(quote)

            _showContinueButton.emit(true)
        }
    }

    private suspend fun typingAnimation(quote: Quote) = withContext(Dispatchers.Main) {
        val text = quote.text
        val author = quote.author

        for (index in text.indices) {
            val letter = text[index]
            val randomQuoteText = randomQuoteText.value.toCharArray().apply {
                this[index] = letter
            }.concatToString()
            _randomQuoteText.emit(randomQuoteText)

            when (letter) {
                '.', ',', ';' -> delay(500)
                else -> delay(30)
            }
        }

        for (index in author.indices) {
            val letter = author[index]
            val randomQuoteAuthor = randomQuoteAuthor.value
            _randomQuoteAuthor.emit(randomQuoteAuthor + letter)
            delay(50)
        }
    }
}
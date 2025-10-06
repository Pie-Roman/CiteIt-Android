package ru.pyroman.citeit.feature.main.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.pyroman.citeit.base.uikit.theme.CiteItTheme
import ru.pyroman.citeit.domain.quote.model.Quote

@Composable
fun QuotesStackView(
    quotes: List<Quote>,
    onQuoteDragEnd: () -> Unit,
) {
    val count = quotes.size
    val numberOfVisible = 3

    if (count > 0) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            quotes.forEachIndexed { index, quote ->
                val reverseIndex = (quotes.lastIndex - index).toDouble()

                if (reverseIndex < numberOfVisible) {
                    QuoteView(
                        quote = quote,
                        index = index,
                        reverseIndex = reverseIndex,
                        textPadding = ((1 - reverseIndex) * 20).toFloat(),
                        isTextShown = reverseIndex < 2,
                        onDragEnd = onQuoteDragEnd,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun QuotesStackViewPreview() {
    CiteItTheme {
        QuotesStackView(
            quotes = listOf(
                Quote(
                    text = "Some quote",
                    author = "Some author",
                ),
                Quote(
                    text = "Some quote",
                    author = "Some author",
                ),
                Quote(
                    text = "Some quote",
                    author = "Some author",
                ),
                Quote(
                    text = "Some quote",
                    author = "Some author",
                ),
                Quote(
                    text = "Some quote",
                    author = "Some author",
                ),
            ),
            onQuoteDragEnd = {},
        )
    }
}


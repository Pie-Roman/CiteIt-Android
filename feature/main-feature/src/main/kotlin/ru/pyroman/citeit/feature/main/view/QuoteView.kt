package ru.pyroman.citeit.feature.main.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.pyroman.citeit.domain.quote.model.Quote

@Composable
fun QuoteView(
    quote: Quote,
    modifier: Modifier,
    isTextShown: Boolean,
) {
    val density = LocalDensity.current
    val screenWidth = LocalWindowInfo.current.containerSize.width
    val screenHeight = LocalWindowInfo.current.containerSize.height

    val width = with(density) { (screenWidth * 0.75f).toFloat().toDp() }
    val height = with(density) { (screenHeight * 0.5f).toFloat().toDp() }

    Box(
        modifier = modifier
            .width(width)
            .height(height)
            .padding(20.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(quote.color.red, quote.color.green, quote.color.blue)),
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "“",
                    color = Color.White,
                    fontSize = 30.sp
                )
            }

            if (isTextShown) {
                Text(
                    text = quote.text,
                    color = Color.White,
                    fontSize = 20.sp,
                    letterSpacing = 1.sp,
                    lineHeight = 30.sp,
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "”",
                    color = Color.White,
                    fontSize = 30.sp
                )
            }
        }
    }
}

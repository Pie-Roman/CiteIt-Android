package ru.pyroman.citeit.feature.main.view

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import ru.pyroman.citeit.domain.quote.model.Quote
import kotlin.math.abs
import kotlin.random.Random

@Composable
fun QuoteView(
    quote: Quote,
    index: Int,
    reverseIndex: Double,
    textPadding: Float,
    isTextShown: Boolean,
    onDragEnd: () -> Unit,
) {
    var offsetX by remember { mutableFloatStateOf(0f) }
    var rotation by remember { mutableFloatStateOf((reverseIndex * 8).toFloat()) }

    val animatedOffsetX by animateFloatAsState(targetValue = offsetX)
    val animatedRotation by animateFloatAsState(targetValue = rotation)
    val backgroundColor = remember { generateRandomColor() }

    val screenWidth = LocalWindowInfo.current.containerSize.width.dp
    val screenHeight = LocalWindowInfo.current.containerSize.height.dp

    Box(
        modifier = Modifier
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { change, dragAmount ->
                        change.consume()
                        if (dragAmount.x < 0) {
                            offsetX += dragAmount.x
                            rotation = offsetX / 10f
                        }
                    },
                    onDragEnd = {
                        if (offsetX < 0 && abs(offsetX) > size.width / 3) {
                            offsetX = -1000f
                            onDragEnd()
                        } else {
                            offsetX = 0f
                            rotation = 0f
                        }
                    }
                )
            }
            .offset(x = (reverseIndex * 35).dp)
            .padding(all = (reverseIndex * 20).dp)
            .rotate(reverseIndex.toFloat() * 8f)
            .graphicsLayer {
                translationX = animatedOffsetX
                rotationZ = animatedRotation
            }
            .width(screenWidth * 0.75f)
            .height(screenHeight * 0.5f)
            .zIndex(index.toFloat())
            .padding(20.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor),
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
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

            // Текст цитаты
            if (isTextShown) {
                Text(
                    text = quote.text,
                    color = Color.White,
                    fontSize = 20.sp,
                    letterSpacing = 1.sp,
                    lineHeight = 30.sp,
                    modifier = Modifier
                        .padding(textPadding.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                )
            }

            // Нижняя кавычка
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

private fun generateRandomColor(): Color {
    fun rand() = (Random.nextFloat() * (0.9f - 0.4f) + 0.4f - 0.1f).coerceIn(0f, 1f)
    return Color(rand(), rand(), rand())
}

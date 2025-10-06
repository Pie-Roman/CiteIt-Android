package ru.pyroman.citeit.feature.main.view

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import ru.pyroman.citeit.domain.quote.model.Quote
import kotlin.math.abs
import kotlin.math.roundToInt

@Composable
fun QuoteView(
    quote: Quote,
    index: Int,
    reverseIndex: Int,
    textPadding: Float,
    isTextShown: Boolean,
    onDragEnd: () -> Unit,
) {
    var offsetX by remember { mutableFloatStateOf((index * 40).toFloat()) }
    var rotation by remember { mutableFloatStateOf((index * 8).toFloat()) }

    val animatedOffsetX by animateFloatAsState(targetValue = offsetX)
    val animatedRotation by animateFloatAsState(targetValue = rotation)

    val density = LocalDensity.current
    val screenWidth = LocalWindowInfo.current.containerSize.width
    val screenHeight = LocalWindowInfo.current.containerSize.height

    val width = with(density) { (screenWidth * 0.75).toFloat().toDp() }
    val height = with(density) { (screenHeight * 0.5).toFloat().toDp() }

    Box(
        modifier = Modifier
            .offset { IntOffset(animatedOffsetX.roundToInt(), 0) }
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
            .padding(all = (index * 20).dp)
            .graphicsLayer {
                translationX = animatedOffsetX
                rotationZ = animatedRotation
            }
            .width(width)
            .height(height)
            .zIndex(reverseIndex.toFloat())
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
                        .padding(textPadding.dp)
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

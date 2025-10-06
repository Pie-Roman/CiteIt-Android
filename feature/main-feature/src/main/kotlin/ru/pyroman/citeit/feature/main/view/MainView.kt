package ru.pyroman.citeit.feature.main.view

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import ru.pyroman.citeit.feature.main.viewmodel.MainViewModel

@Composable
fun MainView(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel(),
){

    val quoteList by viewModel.quoteList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchQuoteList()
    }

    val count = quoteList.size
    val numberOfVisible = 3

    val offsets = remember { mutableStateMapOf<String, Animatable<Float, AnimationVector1D>>() }
    val scope = rememberCoroutineScope()

    if (count > 0) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            quoteList.forEachIndexed { index, quote ->
                val reverseIndex = quoteList.lastIndex - index

                val offsetX = offsets.getOrPut(quote.id) { Animatable(index * 40f) }
                val rotation = derivedStateOf { offsetX.value / 10f }

                if (index < numberOfVisible) {
                    key(quote.id) {
                        QuoteView(
                            quote = quote,
                            modifier = Modifier
                                .graphicsLayer {
                                    translationX = offsetX.value
                                    rotationZ = rotation.value
                                }
                                .pointerInput(Unit) {
                                    if (index == 0) {
                                        detectDragGestures(
                                            onDrag = { change, dragAmount ->
                                                change.consume()
                                                if (dragAmount.x < 0) {
                                                    scope.launch { offsetX.snapTo(offsetX.value + dragAmount.x) }
                                                }
                                            },
                                            onDragEnd = {
                                                if (offsetX.value < -size.width / 3) {
                                                    scope.launch {
                                                        offsetX.animateTo(
                                                            targetValue = -1000f,
                                                            animationSpec = tween(
                                                                durationMillis = 400,
                                                                easing = LinearOutSlowInEasing
                                                            )
                                                        )
                                                        viewModel.removeLastQuote()
                                                    }
                                                } else {
                                                    scope.launch {
                                                        offsetX.animateTo(
                                                            targetValue = 0f,
                                                            animationSpec = tween(
                                                                durationMillis = 400,
                                                                easing = LinearOutSlowInEasing
                                                            )
                                                        )
                                                    }
                                                }
                                            }
                                        )
                                    }
                                }
                                .padding(all = (index * 20).dp)
                                .zIndex(reverseIndex.toFloat()),
                            isTextShown = index < 2,
                        )
                    }

                    scope.launch {
                        offsetX.animateTo(index * 35f, tween(400))
                    }
                }
            }
        }
    }
}
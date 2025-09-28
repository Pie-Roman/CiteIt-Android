package ru.pyroman.citeit.feature.start.view

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.pyroman.citeit.feature.start.viewmodel.StartViewModel

@Composable
fun StartView(
    modifier: Modifier,
    viewModel: StartViewModel = viewModel(),
) {

    val showContinueButtonState by viewModel.showContinueButton.collectAsState()
    val randomQuoteText by viewModel.randomQuoteText.collectAsState()
    val randomQuoteAuthor by viewModel.randomQuoteAuthor.collectAsState()

    val rotation by animateFloatAsState(
        targetValue = if (showContinueButtonState) -720f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow,
        ),
        label = "rotation"
    )

    val offsetY by animateFloatAsState(
        targetValue = if (showContinueButtonState) 0f else 120f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow,
        ),
        label = "offset"
    )

    LaunchedEffect(Unit) {
        viewModel.fetchRandomQuote()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFE9626E))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(300.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 20.dp),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = randomQuoteText,
                    color = Color.White,
                    fontSize = 30.sp,
                    lineHeight = 40.sp,
                    maxLines = Int.MAX_VALUE,
                )

                Spacer(Modifier.height(60.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = randomQuoteAuthor,
                        color = Color.White,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(end = 10.dp)
                    )
                }
            }

            Spacer(Modifier.weight(1f))

            IconButton(
                modifier = Modifier
                    .size(90.dp)
                    .padding(10.dp)
                    .offset(y = offsetY.dp)
                    .rotate(rotation)
                    .alpha(if (showContinueButtonState) 1f else 0f),
                onClick = { /*...*/ },
            ) {
                Icon(
                    modifier = Modifier
                        .size(90.dp)
                        .border(width = 4.dp, color = Color.White, shape = CircleShape),
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Next",
                    tint = Color.White
                )
            }

            Spacer(Modifier.height(60.dp))
        }
    }
}
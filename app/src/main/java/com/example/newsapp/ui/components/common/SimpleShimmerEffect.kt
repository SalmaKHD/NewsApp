package com.example.newsapp.ui.components.common

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import com.example.newsapp.ui.theme.grey200

// grey200, grey700 by default
@Composable
fun Modifier.simpleShimmerEffect(
    backgroundColor: Color = grey200
) = composed {
    val transition = rememberInfiniteTransition()
    val componentAlpha by transition.animateFloat(
        initialValue = 0.9f,
        targetValue = 0.5f,
        animationSpec = infiniteRepeatable(
            repeatMode = RepeatMode.Reverse,
            animation = tween(
                2000,
                easing = FastOutSlowInEasing,
                delayMillis = 0
            )
        ), label = "dsd animation"
    )

    alpha(componentAlpha)
        .background(
            color = backgroundColor
        )
}
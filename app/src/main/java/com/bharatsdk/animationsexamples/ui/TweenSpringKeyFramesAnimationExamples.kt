package com.bharatsdk.animationsexamples.ui

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun TweenExample() {
    var isEnabled by remember {
        mutableStateOf(false)
    }
    val pathAnimation = animateFloatAsState(
        targetValue = if (!isEnabled) 0f else 1f, tween(3000, easing = FastOutSlowInEasing),
        label = "Tween FastOutSlowInEasing Animation"
    )
    LaunchedEffect(key1 = Unit) {
        isEnabled = true
    }
    Canvas(modifier = Modifier.fillMaxSize()) {
        val path = Path().apply {
            moveTo(100f, 100f)
            quadraticBezierTo(500f, 200f, pathAnimation.value * 100, pathAnimation.value *500)
        }

        drawPath(path, color = Color.Black, style = Stroke(width = 10f))
    }
}

@Preview
@Composable
private fun TweenExampleOne() {
    TweenExample()
}
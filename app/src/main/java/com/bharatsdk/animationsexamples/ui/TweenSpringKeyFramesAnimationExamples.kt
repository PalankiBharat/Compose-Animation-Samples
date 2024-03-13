package com.bharatsdk.animationsexamples.ui

import androidx.compose.animation.core.EaseInBounce
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
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
import androidx.compose.ui.geometry.Offset
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
        targetValue = if (!isEnabled) 0f else 1f,
        animationSpec = tween(durationMillis = 3000, easing = FastOutSlowInEasing),
        label = "Tween FastOutSlowInEasing Animation"
    )
    LaunchedEffect(key1 = Unit) {
        isEnabled = true
    }
    Canvas(modifier = Modifier.fillMaxSize()) {
        val path = Path().apply {
            moveTo(100f, 100f)
            quadraticBezierTo(500f, 200f, pathAnimation.value * 100, pathAnimation.value * 500)
        }

        drawPath(path, color = Color.Black, style = Stroke(width = 10f))
    }
}

@Preview
@Composable
private fun TweenExampleOne() {
    TweenExample()
}

@Composable
fun SpringExample() {
    var isEnabled by remember {
        mutableStateOf(false)
    }
    val pathAnimation = animateFloatAsState(
        targetValue = if (!isEnabled) 0f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "Tween FastOutSlowInEasing Animation",
    )



    LaunchedEffect(key1 = Unit) {
        isEnabled = true
    }
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawCircle(
            color = Color.Black,
            radius = 90f,
            center = Offset((pathAnimation.value * 600f) + 100f, 100f)
        )
    }
}

@Composable
fun KeyframeExample() {
    var isEnabled by remember {
        mutableStateOf(false)
    }


    val pathAnimation = animateFloatAsState(
        targetValue = if (!isEnabled) 0f else 1f,
        animationSpec = keyframes {
            //total duration
            durationMillis = 5000
            0.0f at 0 with LinearOutSlowInEasing
            0.2f at 1000
            0.5f at 2500
            0.7f at 4000 with EaseInBounce
        },
        label = "KeyframeExample",
    )
    LaunchedEffect(key1 = Unit) {
        isEnabled = true
    }
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawCircle(
            color = Color.Black,
            radius = 40f,
            center = Offset((pathAnimation.value * 700f) + 100f, 100f)
        )
    }
}

@Preview
@Composable
private fun SpringExamplePrev() {
    SpringExample()
}

@Preview
@Composable
private fun KeyFrameExamplePrev() {
    KeyframeExample()
}


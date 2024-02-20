package com.bharatsdk.animationsexamples.ui

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AnimatedContentBasicExample() {
    Column(modifier = Modifier.fillMaxSize()) {
        var counter by remember {
            mutableIntStateOf(1)
        }
        AnimatedContent(
            targetState = counter,
            label = "Fading counter Animation",
        ) {
            Text(
                text = it.toString(),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                fontSize = 30.sp
            )
        }
        Button(onClick = { counter++ }) {
            Text(text = "Increase Count")
        }
    }
}

@Composable
fun AnimatedContentFadeInFadeOutExample() {
    Column(modifier = Modifier.fillMaxSize()) {
        var counter by remember {
            mutableIntStateOf(1)
        }
        AnimatedContent(
            targetState = counter,
            label = "Fading counter Animation",
            transitionSpec = {
                fadeIn(tween(2000)) togetherWith fadeOut(tween(2000))
            }
        ) {
            Text(
                text = it.toString(),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                fontSize = 30.sp
            )
        }
        Button(onClick = { counter++ }) {
            Text(text = "Increase Count")
        }
    }
}


@Preview
@Composable
fun AnimatedContentBasicExamplePrev() {
    AnimatedContentBasicExample()
}

@Preview
@Composable
fun AnimatedContentFadeInFadeOutPrev() {
    AnimatedContentFadeInFadeOutExample()
}


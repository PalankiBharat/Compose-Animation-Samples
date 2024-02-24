package com.bharatsdk.animationsexamples.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bharatsdk.animationsexamples.R

@Composable
fun AnimatedVisibilityBasicExamples() {
    Box(modifier = Modifier.fillMaxSize()) {
        var isItemVisible by remember {
            mutableStateOf(false)
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            AnimatedVisibility(visible = isItemVisible, modifier = Modifier.size(200.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.kodee),
                    contentDescription = "Kodee"
                )
            }
        }


        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp),
            onClick = { isItemVisible = !isItemVisible }) {
            Text(text = "Hide/Unhide")
        }
    }


}

@Composable
fun AnimatedVisibilityDifferentAnimationsExamples() {
    Box(modifier = Modifier.fillMaxSize()) {
        var isItemVisible by remember {
            mutableStateOf(false)
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            AnimatedVisibility(visible = isItemVisible, modifier = Modifier.size(200.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.kodee),
                    contentDescription = "Kodee"
                )
            }
            AnimatedVisibility(
                visible = isItemVisible,
                modifier = Modifier.size(200.dp),
                enter = fadeIn(animationSpec = spring()),
                exit = fadeOut()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.kodee),
                    contentDescription = "Kodee"
                )
            }
            AnimatedVisibility(
                visible = isItemVisible,
                modifier = Modifier.size(200.dp),
                enter = slideIn(initialOffset = {
                    return@slideIn IntOffset(-1, 0)
                })
            ) {
                Image(
                    painter = painterResource(id = R.drawable.kodee),
                    contentDescription = "Kodee"
                )
            }
        }


        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp),
            onClick = { isItemVisible = !isItemVisible }) {
            Text(text = "Hide/Unhide")
        }
    }


}

@Composable
fun AnimatedVisibilityState() {
    Box(modifier = Modifier.fillMaxSize()) {
        val state = remember {
            MutableTransitionState(false).apply {
                targetState = true
            }
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            AnimatedVisibility(
                visibleState = state,
                modifier = Modifier.size(200.dp),
                enter = fadeIn(animationSpec = tween(2000)),
                exit = fadeOut(animationSpec = tween(2000))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.kodee),
                    contentDescription = "Kodee"
                )
            }
        }

        Column(
            modifier = Modifier.align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                text = when {
                    state.isIdle && state.currentState -> "Visible"
                    !state.isIdle && state.currentState -> "Disappearing"
                    state.isIdle && !state.currentState -> "Invisible"
                    else -> "Appearing"
                }
            )
            Button(
                modifier = Modifier
                    .padding(bottom = 20.dp),
                onClick = { state.targetState = !state.currentState }) {
                Text(text = "Hide/Unhide")
            }
        }


    }
}

@Composable
fun AnimatedVisibilityAnimateOnceOnly() {
    Box(modifier = Modifier.fillMaxSize()) {
        val state = remember {
            MutableTransitionState(false).apply {
                targetState = true
            }
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            AnimatedVisibility(
                visibleState = state,
                modifier = Modifier.size(200.dp),
                enter = fadeIn(animationSpec = tween(2000)),
                exit = fadeOut(animationSpec = tween(2000))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.kodee),
                    contentDescription = "Kodee"
                )
            }
        }
    }

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityDifferentChildDifferentAnimationExamples() {
    Box(modifier = Modifier.fillMaxSize()) {
        var isItemVisible by remember {
            mutableStateOf(false)
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            AnimatedVisibility(
                enter = fadeIn(tween(2000)),
                exit = fadeOut(tween(2000)),
                visible = isItemVisible, modifier = Modifier
                    .fillMaxSize()
            ) {
                Column {
                    Image(
                        modifier = Modifier.animateEnterExit(
                            enter = expandVertically(),
                            exit = shrinkVertically()
                        ),
                        painter = painterResource(id = R.drawable.kodee),
                        contentDescription = "Kodee"
                    )
                    Image(
                        modifier = Modifier.animateEnterExit(
                            enter = scaleIn(tween(1500)), exit = scaleOut(
                                tween(1500)
                            )
                        ),
                        painter = painterResource(id = R.drawable.kodee),
                        contentDescription = "Kodee"
                    )
                }

            }
        }

        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp),
            onClick = { isItemVisible = !isItemVisible }) {
            Text(text = "Hide/Unhide")
        }
    }


}


@Preview
@Composable
fun AnimatedVisibilityExamplesPrev() {
    AnimatedVisibilityBasicExamples()
}

@Preview
@Composable
fun AnimatedVisibilityDifferentAnimationsExamplesPrev() {
    AnimatedVisibilityDifferentAnimationsExamples()
}

@Preview
@Composable
fun AnimatedVisibilityStatePrev() {
    AnimatedVisibilityState()
}

@Preview
@Composable
fun AnimatedVisibilityOnceExamplePrev() {
    AnimatedVisibilityAnimateOnceOnly()
}

@Preview
@Composable
fun AnimatedVisibilityDifferentChildDifferentAnimationExamplesPrev() {
    AnimatedVisibilityDifferentChildDifferentAnimationExamples()
}
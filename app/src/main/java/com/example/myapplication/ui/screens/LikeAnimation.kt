package com.example.myapplication.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LikeAnimation(
    modifier: Modifier = Modifier
) {
    NumberChangeAnimationTextTest()
}

@Composable
fun NumberChangeAnimationTextTest(
    modifier: Modifier = Modifier,
    initialText: String = "1000"
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        var text by remember {
            mutableStateOf(initialText)
        }
        var isUp by remember {
            mutableStateOf(true)
        }

        NumberChangeAnimationText(text = text, isUp = isUp)

        Row(
            modifier = Modifier.width(200.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            listOf(-1, 1).forEach{i ->
                TextButton(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Blue
                    ),
                    onClick = {
                        text = (text.toInt() + i).toString()
                        isUp = if (i == -1) true else false
                    }
                ) {
                    Text(text = if (i == -1) "-1" else "+1", fontSize = 20.sp, color = Color.White)
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NumberChangeAnimationText(
    modifier: Modifier = Modifier,
    text: String,
    isUp: Boolean
) {
//    Text(text = text, fontSize = 72.sp)
    Row(){
//        text.forEach {
//            AnimatedContent(
//                targetState = it,
//                transitionSpec = {
//                    if (isUp) {
//                        slideIntoContainer(AnimatedContentScope.SlideDirection.Up) with
//                                fadeOut() + slideOutOfContainer(AnimatedContentScope.SlideDirection.Up)
//                    } else {
//                        slideIntoContainer(AnimatedContentScope.SlideDirection.Down) with
//                                fadeOut() +  slideOutOfContainer(AnimatedContentScope.SlideDirection.Down)
//                    }
//
//                }
//            ) {
//                Text(text = it.toString(), modifier.padding(horizontal = 6.dp, vertical = 12.dp), fontSize = 72.sp)
//            }
//        }
    }
}
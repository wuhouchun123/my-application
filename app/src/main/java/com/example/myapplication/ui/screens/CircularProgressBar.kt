package com.example.myapplication.ui.screens

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CircularProgressBarBox() {
    CircularProgressBar(0.85f, 400)
}

@Composable
fun CircularProgressBar(
    percentAge: Float,
    totalCount: Int,
    circleWidth: Dp = 10.dp,
) {
    var startPlay by remember { mutableStateOf(false) }
    val currentPercent = animateFloatAsState(
        targetValue = if (startPlay) percentAge else 0f,
        tween(
            durationMillis = 500,
            delayMillis = 0,
        )
    )

    LaunchedEffect(key1 = true) {
        startPlay = true
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Canvas(modifier = Modifier.size(300.dp)) {
            drawArc(
                color = Color.Blue,
                startAngle = -90f,
                sweepAngle = currentPercent.value * 360,
                useCenter = false,
                style = Stroke(circleWidth.toPx(), cap = StrokeCap.Round)
            )
        }

        Text( text = (totalCount*percentAge).toInt().toString(), fontSize = 60.sp, fontWeight = FontWeight.Bold)
    }
}

@Preview
@Composable
fun CircularProgressBarPreview(percentAge: Float = 45f) {

}

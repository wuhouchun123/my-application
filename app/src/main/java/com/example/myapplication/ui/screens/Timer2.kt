package com.example.myapplication.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Timer2() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            contentAlignment = Alignment.Center
        ){
            Timer2(modifier = Modifier.size(200.dp), totalTime = 60L * 1000L)
        }
    }

}

@Composable
fun Timer2(
    modifier: Modifier = Modifier,
    totalTime: Long,
    initValue: Float = 1f
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        var size by remember {
            mutableStateOf(IntSize.Zero)
        }

        var startTimer by remember {
            mutableStateOf(false)
        }

        var currentTime by remember {
            mutableStateOf(totalTime)
        }

        var value by remember {
            mutableStateOf(initValue)
        }


        LaunchedEffect(key1 = currentTime, key2 = startTimer) {
            if (startTimer && currentTime > 0) {
                delay(100L)
                currentTime -= 100L
                value = currentTime / totalTime.toFloat()
            }
        }

        Box(
            contentAlignment =  Alignment.Center,
            modifier = Modifier.onSizeChanged {
                size = it
            }
        ){
            Canvas(modifier = modifier.fillMaxSize()) {
                drawArc(
                    startAngle = -215f,
                    sweepAngle = 250f,
                    useCenter = false,
                    color = Color.Gray,
                    style = Stroke(width = 30f, cap = StrokeCap.Round)
                )
                drawArc(
                    startAngle = -215f,
                    sweepAngle = 250f * value,
                    useCenter = false,
                    color = Color.Blue,
                    style = Stroke(width = 30f, cap = StrokeCap.Round)
                )
                val center = Offset(size.width / 2f, size.height / 2f)
                val beta = (250f * value + 145f) * (PI / 180f).toFloat()
                val r = size.width / 2f
                val a = cos(beta) * r
                val b = sin(beta) * r
                drawPoints(
                    points = listOf(Offset(center.x + a, center.y + b)),
                    color = Color.Blue,
                    pointMode = PointMode.Points,
                    strokeWidth =  60f,
                    cap = StrokeCap.Round
                )
            }

            Text(text = (currentTime/1000L).toString(), fontSize = 48.sp, fontWeight = FontWeight.Bold)

            Button(
                onClick = {
                    startTimer = !startTimer
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (startTimer) Color.Red else Color.Green
                ),
                modifier = Modifier.padding(top = 150.dp)
            ) {
                Text(text = if (startTimer) "停止" else "开始", color =  if (startTimer) Color.White else Color.Black)
            }
        }

    }

}
package com.example.myapplication.ui.screens

import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.myapplication.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import java.time.Clock.offset
import kotlin.math.PI
import kotlin.math.atan2

@Composable
fun DraggableMusicKnob2() {
    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        var volume by remember {
            mutableStateOf(0f)
        }

        val barCount = 40

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .border(1.dp, Color.Green, RoundedCornerShape(10.dp))
                .padding(30.dp)
        ) {
            MusicKnob2(modifier = Modifier.size(100.dp)) {
                volume = it
            }
            Spacer(modifier = Modifier.width(10.dp))
            VolumeBar2(
                modifier = Modifier.fillMaxWidth().height(30.dp),
                actionBars = (volume * barCount).toInt(),
                barCount = barCount
            )
        }
    }
}

@Composable
fun VolumeBar2(
    modifier: Modifier = Modifier,
    actionBars: Int = 0,
    barCount: Int = 10
) {
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        val barWidth = remember {
            constraints.maxWidth / ( 2f * barCount )
        }

        Canvas(modifier = modifier) {
            for ( i in 0 until  barCount ) {
                drawRect(
                    topLeft = Offset(barWidth * i * 2f, 0f),
                    color = if (i in 0 .. actionBars) Color.Red else Color.Gray,
                    size = Size(barWidth, constraints.maxHeight.toFloat())
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MusicKnob2(
    modifier: Modifier = Modifier,
    limitAngle: Float = 25f,
    onChanged: (Float) -> Unit,
) {
    var centerX by remember {
        mutableStateOf(0f)
    }
    var centerY by remember {
        mutableStateOf(0f)
    }
    var moveX by remember {
        mutableStateOf(0f)
    }
    var moveY by remember {
        mutableStateOf(0f)
    }

    var rotation by remember {
        mutableStateOf(limitAngle)
    }

    Image(
        painter = painterResource(id = R.drawable.music_knob),
        contentDescription = "旋钮",
        modifier = modifier
            .fillMaxSize()
            .onGloballyPositioned {
                val bounds = it.boundsInWindow()
                centerX = bounds.size.width / 2f
                centerY = bounds.size.height / 2f
            }
            .pointerInteropFilter { event ->
                moveX = event.x
                moveY = event.y
                val angle = -atan2(centerX - moveX, centerY - moveY) * (180f / PI).toFloat()

                when (event.action) {
                    MotionEvent.ACTION_DOWN,
                    MotionEvent.ACTION_MOVE -> {
                        if (angle !in -limitAngle..limitAngle) {
                            val fixedAngle = if (angle in -180f..-limitAngle) {
                                360f + angle
                            } else {
                                angle
                            }
                            rotation = fixedAngle
                            val percentage =
                                (fixedAngle - limitAngle) / (360f - limitAngle * 2f)
                            onChanged(percentage)
                            true
                        } else false
                    }

                    else -> false
                }

            }
            .rotate(rotation)
    )
}
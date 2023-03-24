package com.example.myapplication.ui.screens

import androidx.compose.runtime.Composable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.drag
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
//import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

@Composable
fun DraggableBall(
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize().background(Color(0xFFF0DBA8))) {
        //获取当前Box的宽高
        val width: Int = with(LocalDensity.current) { maxWidth.toPx() }.toInt()
        val height = with(LocalDensity.current) { maxHeight.toPx() }.toInt()
        Box(
            modifier = Modifier
                .size(45.dp)
                .dragToSide(width, height)
                .background(color = Color(0xFF5E4EAD), shape = CircleShape)
        )
    }
}

fun Modifier.dragToSide(width: Int, height: Int): Modifier = composed {
    val animOffset = remember { Animatable(Offset(0f, 0f), Offset.VectorConverter) }
    offset {
        //Log.i("hj", "animOffset = ${animOffset.value.toString()}")
        IntOffset(animOffset.value.x.roundToInt(), animOffset.value.y.roundToInt())
    }.pointerInput(Unit) {
        // 用于计算抛掷衰减。
        val decay = splineBasedDecay<Offset>(this)
        //对触摸事件和 Animatable 使用挂起函数。
        coroutineScope {
            while (true) {
                // 检测touch down 事件.
                val firstDownPointerId = awaitPointerEventScope { awaitFirstDown().id }
                val velocityTracker = VelocityTracker()
                // 停止正在进行的动画
                animOffset.stop()
                //touch down 之后的一系列touch 事件
                awaitPointerEventScope {
                    drag(firstDownPointerId) { change ->
                        //边界检测防止小球移除到屏幕外面
                        val offsetX = sideDetect(
                            width,
                            size.width,
                            animOffset.value.x + change.positionChange().x
                        )
                        val offsetY = sideDetect(
                            height,
                            size.height,
                            animOffset.value.y + change.positionChange().y
                        )
                        //Log.i("hj", "offsetX = $offsetX , offsetY = $offsetY ")
                        //更新动画的坐标
                        launch {
                            animOffset.snapTo(Offset(offsetX, offsetY))
                        }
                        velocityTracker.addPosition(
                            change.uptimeMillis,
                            change.position
                        )
                    }
                }
                //touch事件结束，准备动画
                val velocity = velocityTracker.calculateVelocity()
                val targetOffset = decay.calculateTargetValue(
                    typeConverter = Offset.VectorConverter,
                    initialValue = animOffset.value,
                    initialVelocity = velocity.toOffset()
                )
                // 动画在到达边界时停止
                animOffset.updateBounds(
                    lowerBound = Offset(0f, 0f),
                    upperBound = Offset(
                        (width - size.width).toFloat(),
                        (height - size.height).toFloat()
                    )
                )
                launch {
                    if (targetOffset.x.absoluteValue <= width / 2f) {
                        animOffset.animateTo(targetValue = Offset(0f, targetOffset.y))
                    } else {
                        animOffset.animateTo(
                            targetValue = Offset(
                                (width - size.width).toFloat(),
                                targetOffset.y
                            )
                        )
                    }
                }
            }
        }
    }
}

fun Velocity.toOffset() = Offset(x, y)

fun sideDetect(parentSize: Int, size: Int, offset: Float): Float {
    return if (offset <= 0) {
        0f
    } else if (offset >= parentSize - size) {
        (parentSize - size).toFloat()
    } else {
        offset
    }
}
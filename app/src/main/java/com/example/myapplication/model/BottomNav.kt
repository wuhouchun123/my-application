package com.example.myapplication.model

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNav(
    val name: String,
    val icon: ImageVector,
    val route: String,
    val badgeCount: Int = 0
    )

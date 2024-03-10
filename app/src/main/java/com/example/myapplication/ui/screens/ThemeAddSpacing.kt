package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.myapplication.ui.theme.LocalSpacing
import com.example.myapplication.ui.theme.spacing

@Composable
fun ThemeAddSpacingBox() {
    ThemeAddSpacing()
}

@Composable
fun ThemeAddSpacing() {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ){
        Box(
            modifier = Modifier
            .background(Color.Black)
            .fillMaxWidth()
            .height(MaterialTheme.spacing.extraSmall)
        )

        Box(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxWidth()
                .height(MaterialTheme.spacing.small)
        )

        Box(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxWidth()
                .height(MaterialTheme.spacing.medium)
        )

        Box(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxWidth()
                .height(MaterialTheme.spacing.large)
        )

        Box(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxWidth()
                .height(MaterialTheme.spacing.extraLarge)
        )
    }
    // LocalSpacing.current.medium

}
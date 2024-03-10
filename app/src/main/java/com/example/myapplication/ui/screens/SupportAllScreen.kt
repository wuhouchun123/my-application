package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.model.WindowInfo
import com.example.myapplication.model.rememberWindowInfo

@Composable
fun SupportAllScreenBox() {
    SupportAllScreen()
}

@Composable
fun SupportAllScreen() {
    val windowInfo = rememberWindowInfo()

    if (windowInfo.screenWidthInfo == WindowInfo.WindowType.Compact) {
        LazyColumn(){
            items(20){
                Box(
                    modifier = Modifier
                        .background(Color.Green)
                        .fillMaxWidth()
                        .height(60.dp),
                ){
                    Text(text = "item $it", fontSize = 32.sp, fontWeight = FontWeight.Bold)
                }
            }
            items(20){
                Box(
                    modifier = Modifier
                        .background(Color.Gray)
                        .fillMaxWidth()
                        .height(40.dp)
                ){
                    Text(text = "item $it", fontSize = 32.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    } else {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(20) {
                    Box(
                        modifier = Modifier
                            .background(Color.Green)
                            .fillMaxWidth()
                            .height(60.dp)
                    ) {
                        Text(
                            text = "item $it",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(20) {
                    Box(
                        modifier = Modifier
                            .background(Color.Gray)
                            .fillMaxWidth()
                            .height(60.dp)
                            .weight(1f)
                    ) {
                        Text(
                            text = "item $it",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
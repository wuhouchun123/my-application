package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.example.myapplication.model.ListItem
import androidx.compose.material3.Icon
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MultiSelectLazyColumnBOx() {
    MultiSelectLazyColumn()
}

@Composable
fun MultiSelectLazyColumn() {
    var items by remember {
        mutableStateOf(
            (1..20).map { i ->
                ListItem(
                    title = "item $i"
                )
            }
        )
    }

    LazyColumn() {
        items(items.size) { i ->
            Row(
                modifier = Modifier
                    .background(if (i % 2 == 0) Color.LightGray else Color.White)
                    .fillMaxSize()
                    .height(60.dp)
                    .clickable {
                        items = items.mapIndexed() { j, item ->
                            if (i == j) {
                                items[j].copy(selected = !items[j].selected)
                            } else item
                        }
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(items[i].title)
                if (items[i].selected) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "ok")
                }
            }
        }
    }
}
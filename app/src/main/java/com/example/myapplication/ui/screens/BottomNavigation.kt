package com.example.myapplication.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BottomNavigation() {
//    Column() {
//        Card(shape = RoundedCornerShape(4.dp), modifier = Modifier.padding(8.dp)) {
//            BottomNavigationAlwaysShowLabelComponent()
//        }
//        Card(shape = RoundedCornerShape(4.dp), modifier = Modifier.padding(8.dp)) {
//            BottomNavigationOnlySelectedLabelComponent()
//        }
//    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ){
        BottomNavigationOnlySelectedLabelComponent()
    }
}

val listItems = listOf("Games", "Apps", "Movies", "Books")

@Composable
fun BottomNavigationAlwaysShowLabelComponent() {
    var selectedIndex by remember { mutableStateOf(0) }
    BottomNavigation() {
        listItems.forEachIndexed { index, label ->
            BottomNavigationItem(
                icon = {
                    Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Favorite")
                },
                label = {
                    Text(text = label)
                },
                selected = selectedIndex == index,
                onClick = { selectedIndex = index }
            )
        }
    }
}

@Composable
fun BottomNavigationOnlySelectedLabelComponent() {
    var selectedIndex by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier,
        contentAlignment = Alignment.BottomCenter
    ) {
        when(selectedIndex) {
            0 -> LikeAnimation()
            1 -> DraggableBall(modifier = Modifier.fillMaxSize())
            2 -> Timer()
            3 -> DraggableMusicKnob()
        }

        BottomNavigation() {
            listItems.forEachIndexed { index, label ->
                BottomNavigationItem(
                    icon = {
                        Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Favorite")
                    },
                    label = {
                        Text(text = label)
                    },
                    selected = selectedIndex == index,
                    onClick = { selectedIndex = index },
                    alwaysShowLabel = false
                )
            }
        }
    }
}

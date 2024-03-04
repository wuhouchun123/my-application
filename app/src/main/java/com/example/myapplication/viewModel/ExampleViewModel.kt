package com.example.myapplication.viewModel

import androidx.lifecycle.ViewModel
import com.example.myapplication.model.AppScreen

class ExampleViewModel: ViewModel() {
    val exampleEntryList = listOf(
        AppScreen.LikeAnimation,
        AppScreen.DraggableMusicKnob,
        AppScreen.DraggableMusicKnob2,
        AppScreen.DraggableBall,
        AppScreen.Timer,
        AppScreen.BottomNavigation,
        AppScreen.Dialog,
        AppScreen.Drawer,
        AppScreen.Chat,
        AppScreen.FixedBall,
        AppScreen.CircularProgressBar
    )
}
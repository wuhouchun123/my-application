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
        AppScreen.Timer2,
        AppScreen.BottomNavigation,
        AppScreen.Dialog,
        AppScreen.Drawer,
        AppScreen.Chat,
        AppScreen.FixedBall,
        AppScreen.CircularProgressBar,
        AppScreen.Animated3DDropDown,
        AppScreen.Navigate,
        AppScreen.SplashScreen,
        AppScreen.BottomNavigationWithBadges,
        AppScreen.MultiSelectLazyColumn,
        AppScreen.PermissionHandling
    )
}
package com.example.myapplication.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.model.AppScreen
import com.example.myapplication.ui.screens.*

@Composable
fun AppNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = AppScreen.Home.name,
    ) {
        composable(route = AppScreen.Home.name) {
            Home(
                onExampleEntryClick = {
                    navController.navigate(it.name)
                }
            )
        }

        composable(route = AppScreen.LikeAnimation.name) {
            LikeAnimation()
        }

        composable(route = AppScreen.DraggableMusicKnob.name) {
            DraggableMusicKnob()
        }

        composable(route = AppScreen.DraggableMusicKnob2.name) {
            DraggableMusicKnob2()
        }

        composable(route = AppScreen.DraggableBall.name) {
            DraggableBall()
        }

        composable(route = AppScreen.Timer.name) {
            Timer()
        }

        composable(route = AppScreen.Timer2.name) {
            Timer2()
        }

        composable(route = AppScreen.BottomNavigation.name) {
            BottomNavigation()
        }

        composable(route = AppScreen.Dialog.name) {
            Dialog()
        }

        composable(route = AppScreen.Drawer.name) {
            Drawer()
        }

        composable(route = AppScreen.Chat.name) {
            Chat()
        }

        composable(route = AppScreen.FixedBall.name) {
            FixedBall()
        }

        composable(route = AppScreen.CircularProgressBar.name) {
            CircularProgressBarBox()
        }

        composable(route = AppScreen.Animated3DDropDown.name) {
            Animated3DDropDown()
        }

        composable(route = AppScreen.Navigate.name) {
            Navigate()
        }

        composable(route = AppScreen.SplashScreen.name) {
            NavSplashScreen()
        }

        composable(route = AppScreen.BottomNavigationWithBadges.name) {
            BottomNavigationWithBadges()
        }
    }
}
package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.model.AppScreen
import com.example.myapplication.ui.components.AppNavHost
import com.example.myapplication.ui.components.MyTopAppBar


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // 去掉状态栏背景
                // WindowCompat.setDecorFitsSystemWindows(window, false)

                // 设置状态栏文字
                 ViewCompat.getWindowInsetsController(LocalView.current)?.isAppearanceLightStatusBars = MaterialTheme.colors.isLight

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App(
    navController: NavHostController = rememberNavController()
) {

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = AppScreen.valueOf(
        backStackEntry?.destination?.route ?: AppScreen.Home.name
    )

    Scaffold(
        backgroundColor = Color(0xFFE8EBED),
        topBar = {
            MyTopAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding) // #1
        ) {
            // or you can directly pass the modifier(#1) to AppNavHost(..)
            AppNavHost(navController)
        }
    }
}


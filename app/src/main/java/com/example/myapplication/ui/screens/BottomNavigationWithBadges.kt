package com.example.myapplication.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BadgedBox
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.model.BottomNav

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BottomNavigationWithBadges() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavBottomBar(
                items = listOf(
                    BottomNav(name = "Home", route = "home", icon = Icons.Default.Home),
                    BottomNav(name = "Contact", route = "contact", icon = Icons.Filled.Create, badgeCount = 5),
                    BottomNav(name = "Message", route = "message", icon = Icons.Filled.Menu),
                    BottomNav(name = "Profile", route = "profile", icon = Icons.Filled.Person, badgeCount = 499)
                ),
                navController = navController
            ){ route ->
                navController.navigate(route)
            }
        }
    ) {
        Navigation(navController)
    }
}

@Composable
fun NavBottomBar(
    items: List<BottomNav>,
    navController: NavHostController,
    onClickItem: (String) -> Unit
) {
    var backStackEntry = navController.currentBackStackEntryAsState()

    BottomNavigation(
        backgroundColor = Color.White,
        elevation = 10.dp
    ) {
        items.forEach() { item ->
            // 不能直接用navController.route，因为不是state，只有state的值才行。
            val selected = item.route == backStackEntry.value?.destination?.route

            BottomNavigationItem(
                selected = selected,
                selectedContentColor = Color.Blue,
                unselectedContentColor = Color.Gray,
                onClick = {
                    onClickItem(item.route)
                },
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (item.badgeCount > 0) {
                            BadgedBox(
                                badge = {
                                    Text(
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .background(Color.Red)
                                            .padding(2.dp),
                                        text = item.badgeCount.toString(),
                                        color = Color.White
                                    )
                                }
                            ) {
                                Icon(imageVector = item.icon, contentDescription = item.name)
                            }
                        } else {
                            Icon(imageVector = item.icon, contentDescription = item.name)
                        }
                    }
                },
                label = {
                    if (selected) {
                        Text(item.name.toString())
                    }
                }
            )
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("home")
            }
        }
        composable("contact") {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("contact")
            }
        }
        composable("message") {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("message")
            }
        }
        composable("profile") {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("profile")
            }
        }
    }
}
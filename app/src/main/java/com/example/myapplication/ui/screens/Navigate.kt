package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.model.NavRoute

@Composable
fun Navigate() {
    NavigatePage()
}

@Composable
fun NavigatePage(
    modifier: Modifier = Modifier
) {
    var navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoute.home.route){
        composable(route = NavRoute.home.route){
            HomeScreen(navController = navController)
        }
        
        composable(
            route = NavRoute.detail.route + "/{name}",
            arguments = listOf(
                navArgument(name = "name") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = "dorunto"
                }
            )
        ) { entry ->
            detailScreen(name = entry.arguments?.getString("name"))
        }
    }

}

@Composable
fun HomeScreen(navController: NavController) {
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var text by remember {
            mutableStateOf("dorunto")
        }

        TextField(
            value = text,
            onValueChange = {
                text = it
            }
        )
        Spacer(modifier = Modifier.height(50.dp))
        Button(onClick = {
            navController.navigate(NavRoute.detail.withArgs(text))
        }) {
            Text("detail")
        }
    }
}

@Composable
fun detailScreen(name: String?) {
    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("hello $name")
    }
}


package com.example.myapplication.model

sealed class NavRoute(val route : String) {
    object home: NavRoute("home")
    object detail: NavRoute("detail")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}

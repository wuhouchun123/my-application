package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.model.AppScreen
import com.example.myapplication.viewModel.ExampleViewModel

@Composable
fun Home(
    exampleViewModel: ExampleViewModel = viewModel(),
    onExampleEntryClick: (AppScreen) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        reverseLayout = true,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val exampleEntryList = exampleViewModel.exampleEntryList
        items(exampleEntryList.size) {
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { onExampleEntryClick(exampleEntryList[it]) },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
            ) {
                Text(
                    text = exampleEntryList[it].title,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
        }
    }
}
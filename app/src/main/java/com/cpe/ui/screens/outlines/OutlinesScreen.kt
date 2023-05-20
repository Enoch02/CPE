@file:OptIn(ExperimentalMaterial3Api::class)

package com.cpe.ui.screens.outlines

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun OutlineScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        topBar = {

        },
        content = {paddingValues ->
            LazyColumn(
                modifier = Modifier.padding(paddingValues),
                content = {}
            )
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    OutlineScreen(rememberNavController())
}
@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.cpe.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cpe.R

@Composable
fun AboutScreen(navController: NavController) {
    val names = listOf(
        "Adesanya Enoch",
        "Olajide Olamide",
        "Alayaki Temitope",
        "Olugbenga Samuel",
        "Bamidele-Ilo Gbolahan",
        "Bakare Daniel",
        "Ogunmodede Joshua",
        "Kolawole Olaoluwa"
    )
    val matricNumbers = listOf(
        "EES/19/20/0065",
        "EES/19/20/0420",
        "EES/19/20/0148",
        "EES/19/20/0453",
        "EES/19/20/0205",
        "EES/19/20/0197",
        "EES/19/20/0376",
        "EES/18/20/0303"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "About") },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        content = {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Navigate back"
                            )
                        }
                    )
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "App icon",
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.2f)
                )

                Divider()

                Text(
                    text = "This app is a group project developed & designed by:",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                LazyColumn(
                    content = {
                        items(
                            count = names.size,
                            itemContent = { index ->
                                ListItem(
                                    headlineContent = { Text(text = names[index]) },
                                    supportingContent = { Text(text = matricNumbers[index]) }
                                )
                            }
                        )
                    }
                )
            }
        }
    )
}


@Preview
@Composable
private fun Preview() {
    AboutScreen(rememberNavController())
}
@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.cpe.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cpe.R
import com.cpe.ui.navigation.Screen


@Composable
fun HomeScreen(modifier: Modifier, navController: NavController) {
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(128.dp),
        ) {
            val portalIntent = remember {
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://stdportal.oouagoiwoye.edu.ng/index.php")
                )
            }

            GridItem(
                label = "OOU Student Portal",
                icon = painterResource(R.drawable.baseline_school_24),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                onClick = {
                    uriHandler.openUri("https://stdportal.oouagoiwoye.edu.ng/index.php")
                    /*context.startActivity(portalIntent)*/
                }
            )

            Spacer(modifier = Modifier.width(16.dp))

            GridItem(
                label = "GPA Calculator",
                icon = painterResource(id = R.drawable.baseline_calculate_24),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                onClick = {
                    navController.navigate(Screen.GpCalculatorScreen.route)
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(128.dp),
        ) {
            GridItem(
                label = "Course Outlines",
                icon = painterResource(id = R.drawable.baseline_menu_book_24),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                onClick = {
                    navController.navigate(Screen.OutlineScreen.route)
                }
            )

            Spacer(modifier = Modifier.width(16.dp))

            GridItem(
                label = "News & Events",
                icon = painterResource(id = R.drawable.newspaper),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                onClick = {
                    navController.navigate(Screen.NewsScreen.route)
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(128.dp),
        ) {
            val webVersionIntent = remember {
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://allukmanniy.github.io/ooucedwebsite/////HOME/Home.html")
                )
            }

            GridItem(
                label = "E-Library",
                icon = painterResource(id = R.drawable.baseline_library_books),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                onClick = {
                    Toast.makeText(context, "Coming soon!", Toast.LENGTH_SHORT).show()
                }
            )

            Spacer(modifier = Modifier.width(16.dp))

            GridItem(
                label = "Web Version",
                icon = painterResource(id = R.drawable.baseline_interwebs_24),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                onClick = {
                    context.startActivity(webVersionIntent)
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(128.dp),
        ) {
            GridItem(
                label = "Chat",
                icon = painterResource(id = R.drawable.baseline_chat_24),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                onClick = {

                }
            )

            Spacer(modifier = Modifier.width(16.dp))

            GridItem(
                label = "Web Version",
                icon = painterResource(id = R.drawable.baseline_interwebs_24),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .alpha(0f),
                onClick = {

                }
            )
        }
    }
}

@Composable
private fun GridItem(
    label: String,
    icon: Painter,
    modifier: Modifier,
    onClick: () -> Unit = {}
) {
    OutlinedCard(
        onClick = onClick,
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                painter = icon,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(40.dp)
            )
            Text(
                text = label,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .width(IntrinsicSize.Max)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewPreview() {
    HomeScreen(modifier = Modifier.fillMaxSize(), rememberNavController())
}

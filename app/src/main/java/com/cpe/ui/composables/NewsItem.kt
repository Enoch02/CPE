package com.cpe.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cpe.R
import com.cpe.data.models.News

@Composable
fun NewsItem(modifier: Modifier, news: News) {
    ElevatedCard(
        modifier = modifier.padding(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        content = {
            Image(
                painter = painterResource(id = R.drawable.keys),
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = news.headline,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    maxLines = 1,
                    softWrap = true,
                    overflow = TextOverflow.Ellipsis,
                    modifier = modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = news.shortDesc)
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    Column {
        NewsItem(
            modifier = Modifier
                .fillMaxWidth(),
            news = News(
                headline = "Soccer is fun",
                shortDesc = "Short description",
                content = "Something interesting"
            )
        )
    }
}
package com.example.newsapp.ui.components.home

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.ui.components.common.SimpleAsyncImage
import com.example.newsapp.ui.helper.formatIsoDate
import com.example.newsapp.ui.model.NewsUiModel
import com.example.newsapp.ui.theme.grey200

@Composable
fun NewsItem(
    item: NewsUiModel.NewsItemUiModel,
    onItemClick: (Int?) -> Unit
) {
    val publishDate by remember {
        mutableStateOf(
            formatIsoDate(item.publishedAt?: "")
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .border(width = 2.dp, color = Color.Gray, shape = RoundedCornerShape(4.dp))
            .clickable {
                onItemClick(item.id)
            }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SimpleAsyncImage(
            model = item.urlToImage ?: "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
        )
        Spacer(modifier = Modifier.size(12.dp))
        Column {
            Text(
                text = item.author ?: "",
                style = MaterialTheme.typography.labelMedium.copy(
                    color = Color.Gray
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = item.title ?: "",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = publishDate,
                style = MaterialTheme.typography.labelMedium.copy(
                    color = Color.Gray
                ),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NewsItemPrev() {
    NewsItem(
        item = NewsUiModel.NewsItemUiModel.getDummyData(),
        onItemClick = {}
    )
}
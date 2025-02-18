package com.example.newsapp.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.ui.components.common.HtmlText
import com.example.newsapp.ui.components.common.SimpleAsyncImage
import com.example.newsapp.ui.helper.formatIsoDate
import com.example.newsapp.ui.helper.openInBrowser
import com.example.newsapp.ui.model.NewsUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    newsItemsViewModel: NewsItemsViewModel,
    navigateBack: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "News Details", fontSize = 18.sp) },
                navigationIcon = {
                    IconButton(onClick = {navigateBack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            NewsDetails(newsItemsViewModel.selectedNewsArticle)
        }
    }
}

@Composable
fun NewsDetails(
    item: NewsUiModel.NewsItemUiModel?
) {
    val publishDate by remember {
        mutableStateOf(
            formatIsoDate(item?.publishedAt?: "")
        )
    }
    val context = LocalContext.current
    item?.urlToImage?.let { imageUrl ->
        SimpleAsyncImage(
            model = imageUrl,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = item?.title ?: "No Title",
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 16.dp)
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = "By ${ item?.author ?: "Unknown"} | ${item?.source ?: "Unknown"}",
        fontSize = 14.sp,
        fontStyle = FontStyle.Italic,
        color = Color.Gray,
        modifier = Modifier.padding(horizontal = 16.dp)
    )

    Text(
        text =  publishDate,
        fontSize = 12.sp,
        color = Color.Gray,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
    )

    Spacer(modifier = Modifier.height(16.dp))

    HtmlText(
        html =  item?.content ?: "No Content Available",
        modifier = Modifier.padding(horizontal = 16.dp)
    )

    Spacer(modifier = Modifier.height(16.dp))

    item?.url?.let { url ->
        Text(
            modifier = Modifier
                .clickable {
                    context.openInBrowser(url)
                }
                .padding(horizontal = 16.dp, vertical = 8.dp),
            text = "Read Full Article",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview() {
//    DetailScreen(
//        newsItem = NewsUiModel.NewsItemUiModel.getDummyData(),
//    )
}
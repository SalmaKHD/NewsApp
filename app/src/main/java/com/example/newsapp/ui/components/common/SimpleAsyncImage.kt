package com.example.newsapp.ui.components.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapp.R

@Composable
fun SimpleAsyncImage(
    model: Any?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    isLoading: Boolean = false,
    loadingImageShape: Shape = RoundedCornerShape(8.dp)
) {
    if(!isLoading) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(model)
                .crossfade(true)
                .build(),
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.ic_image_placeholder),
            error = painterResource(id = R.drawable.ic_image_placeholder),
            modifier = modifier,
            contentScale = contentScale
        )
    } else {
        Box(modifier = modifier
            .clip(loadingImageShape)
            .simpleShimmerEffect())
    }
}
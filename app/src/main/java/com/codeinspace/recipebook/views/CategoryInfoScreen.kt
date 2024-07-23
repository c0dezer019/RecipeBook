package com.codeinspace.recipebook.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import coil.compose.rememberAsyncImagePainter
import com.codeinspace.recipebook.models.Category

@Composable
fun CategoryInfoScreen(category: Category, modifier: Modifier = Modifier) {
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            category.strCategory,
            textAlign = TextAlign.Center
        )
        Image(
            rememberAsyncImagePainter(model = category.strCategoryThumb),
            category.strCategory,
            Modifier
                .wrapContentSize()
                .aspectRatio(1f)
        )
        Text(
            category.strCategoryDescription,
            Modifier.verticalScroll(rememberScrollState()),
            textAlign = TextAlign.Justify
        )
    }
}

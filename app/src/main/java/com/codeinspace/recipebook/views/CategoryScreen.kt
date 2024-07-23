package com.codeinspace.recipebook.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.codeinspace.recipebook.models.Category
import com.codeinspace.recipebook.viewmodels.CategoriesViewModel

@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    categories: List<Category>,
    navToCatInfo: (Category) -> Unit,
    navToCat: (Category) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = modifier
    ) {
        items(categories) { category ->
            CategoryItem(category, navToCatInfo, navToCat)
        }
    }
}

@Composable
fun CategoryItem(category: Category, navToCatInfo: (Category) -> Unit, navToCat: (Category) -> Unit) {
    Row(
        Modifier.clickable { navToCat(category) },
        Arrangement.SpaceBetween
    ) {
        Row {
            Image(
                painter = rememberAsyncImagePainter(model = category.strCategoryThumb),
                contentDescription = category.strCategory,
                modifier = Modifier
                    .width(100.dp)
                    .aspectRatio(1f),
            )
            Text(
                text = category.strCategory,
                color = Color.Black,
                style = TextStyle(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 16.dp),
            )
        }
        Box(
            Modifier
                .wrapContentSize()
                .align(Alignment.CenterVertically)
                .padding(end = 25.dp)
                .clickable { navToCatInfo(category) }
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "More information",
                tint = Color.Blue
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryScreenPreview() {
    val viewModel = CategoriesViewModel()
    val viewState by viewModel.state

    CategoryScreen(Modifier.fillMaxSize(), viewState.list, {}) {}
}

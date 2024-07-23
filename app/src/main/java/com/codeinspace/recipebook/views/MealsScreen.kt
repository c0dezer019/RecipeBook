package com.codeinspace.recipebook.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.codeinspace.recipebook.models.MealNameAndThumbOnly

@Composable
fun MealsScreen(
    modifier: Modifier = Modifier,
    meals: List<MealNameAndThumbOnly>,
    meal: (MealNameAndThumbOnly) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = modifier
    ) {
        items(meals) { item ->
            MealItem(item, meal)
        }
    }
}

@Composable
fun MealItem(item: MealNameAndThumbOnly, meal: (MealNameAndThumbOnly) -> Unit) {
    Row(Modifier.clickable { meal(item) }) {
        Image(
            painter = rememberAsyncImagePainter(model = item.strMealThumb),
            contentDescription = item.strMeal,
            modifier = Modifier
                .width(100.dp)
                .padding(10.dp)
                .aspectRatio(1f)
        )
        Text(
            text = item.strMeal,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 16.dp),
        )
    }
}

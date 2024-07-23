package com.codeinspace.recipebook.views

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.codeinspace.recipebook.R
import com.codeinspace.recipebook.models.Ingredients
import com.codeinspace.recipebook.models.Meal
import com.codeinspace.recipebook.views.partials.YouTubePlayer
import com.codeinspace.recipebook.views.utility.lineHandler

@Composable
fun DetailedMealScreen(modifier: Modifier = Modifier, meal: Meal) {
    val ctx: Context = LocalContext.current

    Box {
        Column(
            modifier = modifier
                .padding(8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                meal.strMeal,
                Modifier.align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )


            Spacer(Modifier.height(14.dp))
            Row {

                Column {
                    Text(
                        stringResource(R.string.ingredients),
                        Modifier.padding(bottom = 16.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                    IngredientList(Modifier.padding(start = 18.dp), meal)

                }
                Column {
                    Image(
                        rememberAsyncImagePainter(model = meal.strMealThumb),
                        meal.strMeal,
                        Modifier
                            .width(195.dp)
                            .aspectRatio(1f)
                            .padding(start = 8.dp, end = 8.dp)
                    )
                }
            }
            Spacer(Modifier.height(16.dp))
            Text(
                stringResource(R.string.instructions),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            YouTubePlayer(meal.strYoutube.removeRange(0, 32), LocalLifecycleOwner.current)
            Text(
                lineHandler(meal.strInstructions.lines()),
                Modifier
                    .padding(start = 18.dp, top = 16.dp)
            )
        }
    }
}

@Composable
private fun IngredientList(modifier: Modifier = Modifier, meal: Meal) {
    val ingredients = Ingredients(meal).ingredients

    Column(modifier.wrapContentSize()) {
        for (item in ingredients) {
            Row(Modifier.wrapContentSize()) {
                Measurement(measurement = item.value)
                Ingredient(ingredient = item.key)
            }
        }
    }
}

@Composable
private fun Ingredient(ingredient: String) {
    Box(Modifier.wrapContentSize()) {
        Text(ingredient)
    }
}

@Composable
private fun Measurement(measurement: String) {
    Box(Modifier.wrapContentSize()) {
        Text("$measurement ")
    }
}

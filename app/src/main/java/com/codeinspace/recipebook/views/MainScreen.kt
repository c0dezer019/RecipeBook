package com.codeinspace.recipebook.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codeinspace.recipebook.interfaces.MealDB
import com.codeinspace.recipebook.viewmodels.BaseViewModel

@Composable
fun <T: MealDB> MainScreen(
    modifier: Modifier = Modifier,
    viewState: BaseViewModel.ModelState<T>,
    screen: @Composable () -> Unit,
) {
    Box(
        modifier
            .fillMaxSize()
    ) {

        when {
            viewState.loading -> {
                CircularProgressIndicator(
                    Modifier
                        .align(Alignment.Center)
                        .width(100.dp)
                )
            }

            viewState.error != null -> {
                Text("${viewState.error}")
            }

            else -> {
                screen()
            }
        }
    }
}

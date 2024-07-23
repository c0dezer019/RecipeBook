package com.codeinspace.recipebook.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codeinspace.recipebook.models.Category
import com.codeinspace.recipebook.models.Meal
import com.codeinspace.recipebook.models.MealNameAndThumbOnly
import com.codeinspace.recipebook.models.Routes
import com.codeinspace.recipebook.viewmodels.CategoriesViewModel
import com.codeinspace.recipebook.viewmodels.MealsViewModel

@Composable
fun App(modifier: Modifier = Modifier, navController: NavHostController) {

    NavHost(navController = navController, startDestination = Routes.CategoryScreen.route) {
        composable(Routes.CategoryScreen.route) {
            val catViewModel: CategoriesViewModel =
                viewModel(factory = CategoriesViewModel.Factory())
            val catState by catViewModel.state

            MainScreen(
                modifier,
                catState
            ) {
                CategoryScreen(modifier, categories = catState.list, navToCatInfo = { cat ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("cat", cat)
                    navController.navigate(Routes.CategoryInfoScreen.route)
                }) { cat ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("cat", cat)
                    navController.navigate(Routes.MealsScreen.route)
                }
            }
        }

        composable(Routes.MealsScreen.route) {
            val category =
                navController.previousBackStackEntry?.savedStateHandle?.get<Category>("cat")?.strCategory
                    ?: Category("", "", "", "").strCategory

            val mealsViewModel: MealsViewModel = viewModel(factory = MealsViewModel.Factory(category))
            val mealsState by mealsViewModel.state

            MainScreen(modifier, mealsState) {
                @Suppress("UNCHECKED_CAST")
                MealsScreen(meals = mealsState.list as List<MealNameAndThumbOnly>) { meal ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("meal", meal)
                    navController.navigate(Routes.MealScreen.route)
                }
            }
        }

        composable(Routes.CategoryInfoScreen.route) {
            val category = navController
                .previousBackStackEntry
                ?.savedStateHandle
                ?.get<Category>("cat")

            if (category != null) {
                CategoryInfoScreen(category = category)
            }
        }

        composable(Routes.MealScreen.route) {
            val meal = navController
                .previousBackStackEntry
                ?.savedStateHandle
                ?.get<MealNameAndThumbOnly>("meal")

            if (meal != null) {
                val mealsViewModel: MealsViewModel = viewModel(factory = MealsViewModel.Factory(meal = meal.idMeal))
                val mealState by mealsViewModel.state

                MainScreen(modifier, mealState) {
                    DetailedMealScreen(meal = mealState.item as Meal)
                }
            }
        }
    }
}

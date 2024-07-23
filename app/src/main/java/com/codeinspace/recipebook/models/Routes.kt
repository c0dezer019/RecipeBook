package com.codeinspace.recipebook.models

sealed class Routes(val route: String) {
    data object CategoryScreen : Routes("categoryscreen")
    data object CategoryInfoScreen : Routes("categoryinfoscreen")
    data object MealsScreen : Routes("mealsscreen")
    data object MealScreen : Routes("mealscreen")
}

package com.codeinspace.recipebook.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.codeinspace.recipebook.interfaces.Meals
import com.codeinspace.recipebook.models.Response
import com.codeinspace.recipebook.services.recipeService

typealias DataFetcher = suspend (cat: String?, meal: String?) -> Response

private val get: DataFetcher = { cat: String?, meal: String? ->
    if (!cat.isNullOrEmpty()) {
        Response.MealsResponse(recipeService.getMealsByCat(cat).meals)
    } else if (!meal.isNullOrEmpty()) {
        Response.MealResponse(recipeService.getMealById(meal).meals)
    } else {
        throw IllegalArgumentException("At least one argument should be provided.")
    }
}

class MealsViewModel(category: String? = null, meal: String? = null) : BaseViewModel<Meals>({
    get(category, meal)
}) {

    override fun handleResponse(response: Response) {
        if (response is Response.MealsResponse) {
            iState.value = iState.value.copy(
                list = response.meals,
                loading = false,
                error = null
            )
        } else if (response is Response.MealResponse) {
            iState.value = iState.value.copy(
                item = response.meals[0],
                loading = false,
                error = null
            )
        }
    }

    class Factory(private val category: String? = null, private val meal: String? = null) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            if (modelClass.isAssignableFrom(MealsViewModel::class.java)) {
                when {
                    !category.isNullOrEmpty() && !meal.isNullOrEmpty() -> {
                        @Suppress("UNCHECKED_CAST")
                        return MealsViewModel(meal = meal) as T
                    }
                    !category.isNullOrEmpty() -> {
                        @Suppress("UNCHECKED_CAST")
                        return MealsViewModel(category) as T
                    }
                    !meal.isNullOrEmpty() -> {
                        @Suppress("UNCHECKED_CAST")
                        return MealsViewModel(meal = meal) as T
                    }
                    else -> throw IllegalArgumentException("At least One argument should be provided.")
                }
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

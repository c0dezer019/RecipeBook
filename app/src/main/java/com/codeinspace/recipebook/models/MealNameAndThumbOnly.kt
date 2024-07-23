package com.codeinspace.recipebook.models

import android.os.Parcelable
import com.codeinspace.recipebook.interfaces.MealDB
import com.codeinspace.recipebook.interfaces.Meals
import kotlinx.parcelize.Parcelize

@Parcelize
data class MealNameAndThumbOnly(
    val strMeal: String,
    val strMealThumb: String,
    val idMeal: String
) : Parcelable, Meals, MealDB

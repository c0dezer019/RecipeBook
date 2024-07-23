package com.codeinspace.recipebook.models

import android.os.Parcelable
import com.codeinspace.recipebook.interfaces.MealDB
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
) : Parcelable, MealDB

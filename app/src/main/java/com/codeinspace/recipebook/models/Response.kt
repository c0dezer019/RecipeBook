package com.codeinspace.recipebook.models

sealed class Response {
    data class MealResponse(val meals: List<Meal>) : Response()
    data class MealsResponse(val meals: List<MealNameAndThumbOnly>) : Response()
    data class CatResponse(val categories: List<Category>) : Response()
}

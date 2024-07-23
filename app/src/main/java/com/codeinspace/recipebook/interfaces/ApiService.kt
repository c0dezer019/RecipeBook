package com.codeinspace.recipebook.interfaces

import com.codeinspace.recipebook.models.Meal
import com.codeinspace.recipebook.models.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("categories.php")
    suspend fun getCategories(): Response.CatResponse

    @GET("filter.php")
    suspend fun getMealsByCat(@Query("c") category: String): Response.MealsResponse

    @GET("lookup.php")
    suspend fun getMealById(@Query("i") id: String): Response.MealResponse
}


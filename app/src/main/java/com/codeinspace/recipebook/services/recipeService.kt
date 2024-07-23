package com.codeinspace.recipebook.services

import com.codeinspace.recipebook.interfaces.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val recipeService: ApiService = retrofit.create(ApiService::class.java)

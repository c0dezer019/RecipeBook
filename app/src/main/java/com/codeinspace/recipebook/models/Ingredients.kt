package com.codeinspace.recipebook.models


import kotlin.reflect.full.memberProperties

data class Ingredients (
    private val _meal: Meal,
    val ingredients: MutableMap<String, String> = mutableMapOf()
) {
    init {
        writeIngredients(_meal)
    }

    private fun writeIngredients(meal: Meal) {
        val tempIngredients = mutableMapOf<String, String?>()
        val tempMeasures = mutableMapOf<String, String?>()

        for (prop in Meal::class.memberProperties) {
            when {
                prop.name.contains("strIngredient") -> {
                    val name = prop.call(meal)?.toString()

                    if (!name.isNullOrEmpty()) {
                        tempIngredients[prop.name] = name
                    }
                }

                prop.name.contains("strMeasure") -> {
                    val value = prop.call(meal)?.toString()

                    if (!value.isNullOrEmpty()) {
                        tempMeasures[prop.name] = value
                    }
                }
            }
        }

        for ((k, ingredient) in tempIngredients) {
            val i = k.filter { it.isDigit() }
            val mKey = "strMeasure$i"
            val measure = tempMeasures[mKey] ?: ""

            if (ingredient != null) {
                ingredients[ingredient] = measure
            }
        }
    }
}

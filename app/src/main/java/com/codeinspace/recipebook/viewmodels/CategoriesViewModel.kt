package com.codeinspace.recipebook.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.codeinspace.recipebook.models.Category
import com.codeinspace.recipebook.models.Response
import com.codeinspace.recipebook.services.recipeService

class CategoriesViewModel : BaseViewModel<Category>(
    {
        val res = recipeService.getCategories()
        Response.CatResponse(res.categories)
    }
) {
    override fun handleResponse(response: Response) {
        if (response is Response.CatResponse) {
            iState.value = iState.value.copy(
                list = response.categories,
                loading = false,
                error = null
            )
        }
    }

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            if (modelClass.isAssignableFrom(CategoriesViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CategoriesViewModel() as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

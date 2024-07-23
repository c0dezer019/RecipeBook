package com.codeinspace.recipebook.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeinspace.recipebook.interfaces.MealDB
import com.codeinspace.recipebook.models.Response
import kotlinx.coroutines.launch

abstract class BaseViewModel<T: MealDB>(internal val f: suspend () -> Response) : ViewModel() {
    internal val iState = mutableStateOf(ModelState<T>())
    val state: State<ModelState<T>> = iState

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                val res = f()
                handleResponse(res)
            } catch (e: Exception) {
                iState.value = iState.value.copy(
                    loading = false,
                    error = "Error fetching data: ${e.message}"
                )
            }
        }
    }

    abstract fun handleResponse(response: Response)

    data class ModelState<T: MealDB>(
        val loading: Boolean = true,
        val list: List<T> = emptyList(),
        val item: T? = null,
        val error: String? = null
    )
}

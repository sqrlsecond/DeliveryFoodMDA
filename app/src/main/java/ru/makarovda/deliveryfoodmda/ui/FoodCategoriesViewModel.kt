package ru.makarovda.deliveryfoodmda.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.makarovda.deliveryfoodmda.FoodDeliveryApp
import ru.makarovda.deliveryfoodmda.domain.IRepository
import ru.makarovda.deliveryfoodmda.domain.RequestState

class FoodCategoriesViewModel(private val repository: IRepository): ViewModel() {

    private val _categoriesFlow = MutableStateFlow<RequestState>(RequestState.InProgress)
    val categoriesFlow: StateFlow<RequestState>
        get() = _categoriesFlow

    fun requestCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFoodCategories().collect {
                _categoriesFlow.value = it
            }
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val component = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FoodDeliveryApp)
                    .appComponent
                FoodCategoriesViewModel(component.getRepository())
            }
        }
    }
}
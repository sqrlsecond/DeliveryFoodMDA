package ru.makarovda.deliveryfoodmda.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.makarovda.deliveryfoodmda.FoodDeliveryApp
import ru.makarovda.deliveryfoodmda.domain.IRepository
import ru.makarovda.deliveryfoodmda.domain.RequestState

class DishesViewModel(private val repository: IRepository): ViewModel() {

    private val _dishesFlow = MutableStateFlow<RequestState>(RequestState.InProgress)
    val dishesFlow: StateFlow<RequestState>
        get() = _dishesFlow


    val dishesTegs = HashSet<String>(10)

    fun requestCategories() {
        dishesTegs.clear()
        dishesTegs.add("Всё меню")
        viewModelScope.launch(Dispatchers.IO) {
            repository.getDishes().collect {
                _dishesFlow.value = it
                if (it is RequestState.DishesSuccess){
                    it.dishes.forEach { dish ->
                        dishesTegs.addAll(dish.tegs)
                    }
                }

            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val component = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FoodDeliveryApp)
                    .appComponent
                DishesViewModel(component.getRepository())
            }
        }
    }
}
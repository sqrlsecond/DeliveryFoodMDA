package ru.makarovda.deliveryfoodmda.domain

sealed class RequestState{
    object InProgress : RequestState()
    class FoodCategoriesSuccess (val categories: List<FoodCategory>): RequestState()
    class DishesSuccess (val dishes: List<Dish>): RequestState()
    class Error(val message: String): RequestState()
}

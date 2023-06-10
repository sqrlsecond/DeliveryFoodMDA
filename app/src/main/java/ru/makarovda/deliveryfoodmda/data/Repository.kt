package ru.makarovda.deliveryfoodmda.data


import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import ru.makarovda.deliveryfoodmda.domain.Dish
import ru.makarovda.deliveryfoodmda.domain.FoodCategory
import ru.makarovda.deliveryfoodmda.domain.IRepository
import ru.makarovda.deliveryfoodmda.domain.RequestState
import javax.inject.Inject

class Repository @Inject constructor(private val foodService: IFoodService): IRepository {

    override suspend fun getFoodCategories(): Flow<RequestState> = flow {
        val response = foodService.getFoodCategories()
        if (response.isSuccessful) {
            response.body()?.let{
                //Log.d("FoodCategories", it.categories.toString())
                emit(RequestState.FoodCategoriesSuccess(it.categories))
                return@flow
            }
        }
        val message = response.errorBody()?.toString() ?: "food categories request fail"
        emit(RequestState.Error(message))
    }

    override suspend fun getDishes(): Flow<RequestState> = flow {
        val response = foodService.getAllDishes()

        if (response.isSuccessful) {
            response.body()?.let{
                //Log.d("FoodCategories", it.categories.toString())
                emit(RequestState.DishesSuccess(it.dishes))
                return@flow
            }
        }
        val message = response.errorBody()?.toString() ?: "dishes request fail"
        emit(RequestState.Error(message))
    }
}
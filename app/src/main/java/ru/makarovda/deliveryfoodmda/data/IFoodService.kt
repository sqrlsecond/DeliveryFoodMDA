package ru.makarovda.deliveryfoodmda.data

import retrofit2.Response
import retrofit2.http.GET
import ru.makarovda.deliveryfoodmda.domain.Dish
import ru.makarovda.deliveryfoodmda.domain.FoodCategory

interface IFoodService {

    @GET("./058729bd-1402-4578-88de-265481fd7d54")
    suspend fun getFoodCategories(): Response<FoodCategoriesRemote>

    @GET("./c7a508f2-a904-498a-8539-09d96785446e")
    suspend fun getAllDishes(): Response<DishesRemote>
}
package ru.makarovda.deliveryfoodmda.data

import com.google.gson.annotations.SerializedName
import ru.makarovda.deliveryfoodmda.domain.FoodCategory

data class FoodCategoriesRemote(
    @SerializedName("сategories")
    val categories: List<FoodCategory>
)

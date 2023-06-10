package ru.makarovda.deliveryfoodmda.domain

import com.google.gson.annotations.SerializedName


data class FoodCategory(
    val id: Int,
    val name: String,
    @SerializedName("image_url")
    val imageUrl: String
)

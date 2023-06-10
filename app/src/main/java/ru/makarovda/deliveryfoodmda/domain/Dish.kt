package ru.makarovda.deliveryfoodmda.domain

import com.google.gson.annotations.SerializedName

data class Dish(
    val id: Int,
    val name: String,
    val price: Int,
    val weight: Int,
    val description: String,
    @SerializedName("image_url")
    val imageUrl: String,
    val tegs: List<String>
)

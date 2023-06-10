package ru.makarovda.deliveryfoodmda.domain

import kotlinx.coroutines.flow.Flow

interface IRepository {

    suspend fun getFoodCategories(): Flow<RequestState>

    suspend fun getDishes(): Flow<RequestState>
}
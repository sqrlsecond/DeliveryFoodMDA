package ru.makarovda.weatherappup.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.makarovda.deliveryfoodmda.data.IFoodService
import javax.inject.Singleton


const val baseUrl = "https://run.mocky.io/v3/"

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun getFoodService(): IFoodService {

        val httpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()

        return retrofit.create(IFoodService::class.java)
    }
}
package ru.makarovda.weatherappup.di

import dagger.Component
import ru.makarovda.deliveryfoodmda.data.Repository
import javax.inject.Singleton

@Singleton
@Component(modules=[NetworkModule::class])
interface AppComponent {

    fun getRepository(): Repository
}
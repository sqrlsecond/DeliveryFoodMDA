package ru.makarovda.deliveryfoodmda

import android.app.Application
import ru.makarovda.weatherappup.di.AppComponent
import ru.makarovda.weatherappup.di.DaggerAppComponent

class FoodDeliveryApp: Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()

    }
}
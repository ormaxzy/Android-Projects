package com.example.cityweather

import android.app.Application
import com.example.cityweather.dagger.AppComponent
import com.example.cityweather.dagger.AppModule
import com.example.cityweather.dagger.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {

        super.onCreate()

        appComponent = DaggerAppComponent

            .builder()

            .appModule(AppModule(this))

            .build()

    }

}


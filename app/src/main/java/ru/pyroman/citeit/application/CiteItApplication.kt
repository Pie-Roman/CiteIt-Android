package ru.pyroman.citeit.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import ru.pyroman.citeit.application.di.AppComponent
import ru.pyroman.citeit.application.di.DaggerAppComponent

@HiltAndroidApp
class CiteItApplication : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        appComponent = DaggerAppComponent
            .factory()
            .create(
                application = this
            )
        super.onCreate()
    }
}
package ru.pyroman.citeit.application.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.pyroman.citeit.activity.di.MainActivityComponent

@Component(modules = [AppModule::class])
@ApplicationScope
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application,
        ): AppComponent
    }

    fun mainActivityComponentFactory(): MainActivityComponent.Factory
}
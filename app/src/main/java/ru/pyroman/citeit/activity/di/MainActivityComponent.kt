package ru.pyroman.citeit.activity.di

import dagger.Subcomponent
import ru.pyroman.citeit.activity.MainActivity
import ru.pyroman.citeit.feature.main.di.MainFeatureModule
import ru.pyroman.citeit.feature.start.di.StartFeatureModule

@Subcomponent(modules = [
    StartFeatureModule::class,
    MainFeatureModule::class,
])
@MainActivityScope
interface MainActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainActivityComponent
    }

    fun inject(instance: MainActivity)
}
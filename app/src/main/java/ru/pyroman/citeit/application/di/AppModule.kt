package ru.pyroman.citeit.application.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck

@Module
@DisableInstallInCheck
interface AppModule {

    companion object {

        @Provides
        @ApplicationScope
        fun applicationContext(
            application: Application,
        ): Context {
            return application.applicationContext
        }
    }
}
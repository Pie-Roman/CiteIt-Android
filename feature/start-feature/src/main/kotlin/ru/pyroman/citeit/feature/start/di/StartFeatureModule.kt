package ru.pyroman.citeit.feature.start.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.pyroman.citeit.data.quote.di.QuoteDataModule

@Module(includes = [
    QuoteDataModule::class
])
@InstallIn(ViewModelComponent::class)
interface StartFeatureModule
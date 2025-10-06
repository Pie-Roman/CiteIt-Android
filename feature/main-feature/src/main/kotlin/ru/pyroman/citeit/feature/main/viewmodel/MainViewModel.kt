package ru.pyroman.citeit.feature.main.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.pyroman.citeit.domain.quote.repository.QuoteRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: QuoteRepository,
) : ViewModel() {

}
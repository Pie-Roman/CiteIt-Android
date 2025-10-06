package ru.pyroman.citeit.feature.main.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import ru.pyroman.citeit.feature.main.viewmodel.MainViewModel

@Composable
fun MainView(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel(),
){

    val quoteList by viewModel.quoteList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchQuoteList()
    }

    QuotesStackView(
        quotes = quoteList,
        onQuoteDragEnd = {
            viewModel.removeLastQuote()
        },
    )
}
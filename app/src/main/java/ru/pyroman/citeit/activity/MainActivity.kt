package ru.pyroman.citeit.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import ru.pyroman.citeit.activity.di.MainActivityComponent
import ru.pyroman.citeit.application.CiteItApplication
import ru.pyroman.citeit.base.uikit.theme.CiteItTheme
import ru.pyroman.citeit.common.navigation.Navigation


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var activityComponent: MainActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent = (applicationContext as CiteItApplication)
            .appComponent
            .mainActivityComponentFactory()
            .create()
        activityComponent.inject(this)

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            CiteItTheme {
                ContentView()
            }
        }
    }
}

@Composable
fun ContentView() {
    Navigation()
}

@Preview(showBackground = true)
@Composable
fun ContentViewPreview() {
    CiteItTheme {
        ContentView()
    }
}
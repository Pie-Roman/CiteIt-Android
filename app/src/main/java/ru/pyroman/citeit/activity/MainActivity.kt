package ru.pyroman.citeit.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import ru.pyroman.citeit.activity.di.MainActivityComponent
import ru.pyroman.citeit.application.CiteItApplication
import ru.pyroman.citeit.base.uikit.theme.CiteItTheme
import ru.pyroman.citeit.base.uikit.theme.Purple80
import ru.pyroman.citeit.feature.start.view.StartView


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

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
               scrim = Purple80.toArgb(),
                darkScrim = Purple80.toArgb(),
            ),
            navigationBarStyle = SystemBarStyle.auto(
                lightScrim = Purple80.toArgb(),
                darkScrim = Purple80.toArgb(),
            ),
        )

        setContent {
            CiteItTheme {
                ContentView(
                    modifier = Modifier.padding()
                )
            }
        }
    }
}

@Composable
fun ContentView(modifier: Modifier = Modifier) {
    StartView(modifier)
}

@Preview(showBackground = true)
@Composable
fun ContentViewPreview() {
    CiteItTheme {
        ContentView()
    }
}
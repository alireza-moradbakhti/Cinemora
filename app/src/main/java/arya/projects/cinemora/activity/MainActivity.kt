package arya.projects.cinemora.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import arya.projects.cinemora.navigation.RootNavGraph
import arya.projects.cinemora.ui.theme.CinemoraTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CinemoraTheme {
                val navController = rememberNavController()
                RootNavGraph(navController)
            }
        }
    }
}
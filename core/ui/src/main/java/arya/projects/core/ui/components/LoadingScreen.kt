package arya.projects.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import arya.projects.core.ui.theme.BackgroundLoading
import arya.projects.core.ui.theme.ProgressBarIndicator


@Composable
@Preview(showBackground = true)
fun LoadingScreen(
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = BackgroundLoading),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = ProgressBarIndicator,
        )
    }

}
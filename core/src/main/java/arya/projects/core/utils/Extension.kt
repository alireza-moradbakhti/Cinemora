package arya.projects.core.utils

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.navOptions
import java.util.Locale

/**
 * Displays a short-duration Toast message.
 *
 * This extension function simplifies showing a [Toast] message from any [Context].
 * It defaults to a short duration.
 *
 * @param message The text to be displayed in the Toast.
 */
fun showMessage(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

/**
 * Returns a copy of this string having its first character capitalized,
 * or the original string if it's empty.
 */
fun String.capitalizeFirst(): String {
    if (isEmpty()) {
        return this
    }
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
    }
}

/**
 * Safely navigates to a route if it's not already the current destination.
 * Prevents duplicate navigation events (e.g. rapid double-clicks).
 * @param route The route to navigate to.
 * @param builder An optional lambda to customize the navigation options.
 */
fun NavController.safeNavigate(
    route: String,
    builder: (NavOptionsBuilder.() -> Unit)? = null
) {
    val currentRoute = currentBackStackEntry?.destination?.route
    if (currentRoute != route) {
        try {
            if (builder != null) {
                navigate(route, navOptions(builder))
            } else {
                navigate(route)
            }
        } catch (e: IllegalArgumentException) {
            // Destination might not exist in current graph
            e.printStackTrace()
        } catch (e: IllegalStateException) {
            // Navigation already in progress or not ready
            e.printStackTrace()
        }
    }
}




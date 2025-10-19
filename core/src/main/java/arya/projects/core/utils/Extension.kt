package arya.projects.core.utils

import android.content.Context
import android.widget.Toast
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



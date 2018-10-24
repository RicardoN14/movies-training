package pt.inm.movies.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Context.dial(number: String) = try {
    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
    startActivity(intent)
    true
} catch (e: Exception) {
    e.printStackTrace()
    false
}

fun Context.hideKeyboard(v: View?) {
    if (v != null) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(v.windowToken, 0)
    }
}
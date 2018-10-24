package pt.inm.movies.helpers

import android.content.SharedPreferences
import android.util.Log
import pt.inm.movies.MoviesTrainingApplication
import pt.inm.webrequests.utils.DLog

const val SHARE_PREFERENCES_HELPER_TAG = "SharedPreferencesHelper"

fun <T> savePreference(key: String, value: T) {
    val editor: SharedPreferences.Editor? = MoviesTrainingApplication.instance.sharedPreferences?.edit()

    if (editor != null) {
        when (value) {
            is Int -> editor.putInt(key, value)
            is Long -> editor.putLong(key, value)
            is Boolean -> editor.putBoolean(key, value)
            is String -> editor.putString(key, value)
            is Float -> editor.putFloat(key, value)
            else -> DLog.e(SHARE_PREFERENCES_HELPER_TAG, "value type not supported - $value")
        }
        val preferenceSaved = editor.commit()

        if (!preferenceSaved) {
            Log.e(SHARE_PREFERENCES_HELPER_TAG, "preference not saved! key = $key")
        }
    }
}

fun getBooleanPreference(key: String, fallback: Boolean = false): Boolean{
    return MoviesTrainingApplication.instance.sharedPreferences?.getBoolean(key, fallback) ?: fallback
}

fun getStringPreference(key: String, fallback: String? = null): String?{
    return MoviesTrainingApplication.instance.sharedPreferences?.getString(key, fallback) ?: fallback
}

fun getLongPreference(key: String, fallback: Long = 0): Long{
    return MoviesTrainingApplication.instance.sharedPreferences?.getLong(key, fallback) ?: fallback
}

fun getIntPreference(key: String, fallback: Int = 0): Int{
    return MoviesTrainingApplication.instance.sharedPreferences?.getInt(key, fallback) ?: fallback
}

fun getFloatPreference(key: String, fallback: Float = 0f): Float{
    return MoviesTrainingApplication.instance.sharedPreferences?.getFloat(key, fallback) ?: fallback
}
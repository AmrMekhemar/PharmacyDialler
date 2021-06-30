package com.team.myapplication

import android.content.Context

private const val SHARED_PREFS = "1001"
private const val RESULT_KEY = "1002"
private const val PHOTO_KEY = "1003"

/**
 * Shared prefs manager
 *
 * @property context
 * @constructor Creates a SharedPrefsManager object
 */
class SharedPrefsManager(private val context: Context) {
    var token: String? = ""
        get() = sharedPrefs().getString(RESULT_KEY, "")
        set(value) {
            sharedPrefs().edit().putString(RESULT_KEY, value).apply()
            field = value
        }

    var profilePhotoString: String? = ""
        get() = sharedPrefs().getString(PHOTO_KEY, "")
        set(value) {
            sharedPrefs().edit().putString(PHOTO_KEY, value).apply()
            field = value
        }

    private fun sharedPrefs() =
        context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
}
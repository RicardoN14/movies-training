package pt.inm.movies

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatDelegate
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric
import pt.inm.inmhockeykit.android.HockeyKit
import pt.inm.movies.helpers.LanguageHelper

class MoviesTrainingApplication : Application() {

    companion object {
        lateinit var instance: MoviesTrainingApplication
            private set

        fun getString(@StringRes rscId: Int): String = instance.getString(rscId)
    }

    var sharedPreferences: SharedPreferences? = null
        private set


    /**
     * Handle language selected for the app using LanguageHelper
     **/
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LanguageHelper.onAttach(base, LanguageHelper.getLanguage(base)))
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        tryInitHockeyKit()
        tryInitFabric()
        initSharedPreferences()
    }

    private fun tryInitHockeyKit() {
        if (BuildConfig.CHECK_FOR_UPDATES && !BuildConfig.DEBUG) {
            HockeyKit.init(applicationContext, getString(R.string.app_name),
                    getString(R.string.hockey_url), BuildConfig.DEBUG)
        }
    }

    private fun initSharedPreferences() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
    }

    private fun tryInitFabric() {
        if (!BuildConfig.DEBUG) {
            Fabric.with(this, Crashlytics())
        }
    }

}
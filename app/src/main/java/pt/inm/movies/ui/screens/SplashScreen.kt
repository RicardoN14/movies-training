package pt.inm.movies.ui.screens

import android.view.animation.BounceInterpolator
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.screen_splash.*
import org.jetbrains.anko.startActivity
import pt.inm.movies.R
import pt.inm.movies.constants.PreferenceKeys
import pt.inm.movies.extensions.setEndAnimation
import pt.inm.movies.helpers.getBooleanPreference
import pt.inm.movies.helpers.savePreference

class SplashScreen : Screen() {

    companion object {
        const val SPLASH_DURATION = 1500L
    }

    override fun getLayoutResourceId() = R.layout.screen_splash

    override fun doInitializations() {
        splashLabel.animate().rotationXBy(360f)
                .setEndAnimation { onEndAnimation() }
                .setInterpolator(BounceInterpolator())
                .setDuration(SPLASH_DURATION).start()

        showFirstLaunchSnackbar()
    }

    private fun showFirstLaunchSnackbar() {
        if(getBooleanPreference(PreferenceKeys.FIRST_LAUNCH_KEY, true)) {
            Snackbar.make(screenSplashRootView, R.string.splash_screen_is_first_launch,
                    Snackbar.LENGTH_SHORT).show()
            savePreference(PreferenceKeys.FIRST_LAUNCH_KEY, false)
        }
    }

    private fun onEndAnimation() {
        startActivity<MainScreen>()
    }

}
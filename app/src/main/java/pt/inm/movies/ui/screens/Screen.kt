package pt.inm.movies.ui.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.screen_base.*
import pt.inm.inmhockeykit.android.HockeyKit
import pt.inm.movies.BuildConfig
import pt.inm.movies.R
import pt.inm.movies.entities.dialogs.DialogInfo
import pt.inm.movies.helpers.LanguageHelper
import pt.inm.movies.interfaces.IDialogListener
import pt.inm.movies.managers.DialogManager
import pt.inm.webrequests.utils.DLog

abstract class Screen : AppCompatActivity(), IDialogListener {

    protected val logTag: String = javaClass.simpleName

    val inflater: LayoutInflater by lazy {
        LayoutInflater.from(this)
    }

    private val dialogManager by lazy {
        DialogManager(this)
    }

    var isInBackground = false
        private set

    /**
     * Implementations must return here the Screen layout resource file
     * */
    abstract fun getLayoutResourceId(): Int

    /**
     * Implementations must use this function to
     * do the initializations (for example add listeners to views)
     *
     * This is called after the layout inflate
     */
    abstract fun doInitializations()

    /**
     * Handle language selected for the app using LanguageHelper
     **/
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LanguageHelper.onAttach(newBase))
        DLog.d(logTag, "attachBaseContext")
    }

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DLog.d(logTag, "onCreate bundle = $savedInstanceState")

        setContentView(R.layout.screen_base)

        inflateLayout()
        doInitializations()
        tryCheckForUpdates()
    }

    private fun inflateLayout() {
        val view = LayoutInflater.from(this).inflate(getLayoutResourceId(),
                screenRootLayout, false)

        screenRootLayout.addView(view, 0, screenRootLayout.layoutParams)
    }

    private fun tryCheckForUpdates() {
        if (BuildConfig.CHECK_FOR_UPDATES && !BuildConfig.DEBUG) { // DEV - QUA release
            HockeyKit.checkForUpdate(this, R.mipmap.ic_launcher)
        }
    }

    override fun onStart() {
        super.onStart()
        DLog.d(logTag, "onStart")
    }

    override fun onResume() {
        super.onResume()
        DLog.d(logTag, "onResume")
        isInBackground = false
    }

    override fun onPause() {
        super.onPause()
        DLog.d(logTag, "onPause")
    }

    override fun onStop() {
        super.onStop()
        isInBackground = true
        DLog.d(logTag, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        DLog.d(logTag, "onDestroy")
    }

    /** Dialog Public Functions **/
    fun showDialog(dialogId: String, dialogInfo: DialogInfo, extras: Bundle? = null,
                   dialogListener: IDialogListener? = null) {
        dialogManager.showDialog(dialogId, dialogInfo,
                dialogListener ?: this, extras)
    }

    /**
     * This function must be override if implementations
     * need to do something in reaction to Back Pressed action and should return true if
     * the event was consumed **/
    protected open fun doOnBackPressed(): Boolean = false

    override fun onBackPressed() {
        if (dialogManager.isShowingDialog()) {
            dialogManager.hideDialog(true)
        } else {
            if (!doOnBackPressed()) {
                super.onBackPressed()
            }
        }
    }

}


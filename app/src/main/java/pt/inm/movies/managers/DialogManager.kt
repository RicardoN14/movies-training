package pt.inm.movies.managers

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.dialog_view.*
import pt.inm.movies.R
import pt.inm.movies.entities.dialogs.DialogInfo
import pt.inm.movies.extensions.gone
import pt.inm.movies.extensions.visible
import pt.inm.movies.interfaces.IDialogListener
import pt.inm.movies.ui.screens.Screen

class DialogManager(screen: Screen) : BaseManager<Screen>(screen) {

    private val showAnimation by lazy {
        AnimationUtils.loadAnimation(screen, R.anim.fade_in_slide_up)
    }

    private val hideAnimation by lazy {
        AnimationUtils.loadAnimation(screen, R.anim.fade_out_slide_down)
    }

    private var currentDialogInfo: DialogInfo? = null
    private var currentDialogListener: IDialogListener? = null
    private var currentDialogId: String? = null
    private var currentDialogExtras: Bundle? = null
    private var customView: View? = null

    init {
        addListeners()
    }

    private fun addListeners() {
        screen.dialogNegativeButton.setOnClickListener { onNegativeButtonClicked() }
        screen.dialogPositiveButton.setOnClickListener { onPositiveButtonClicked() }
        screen.dialogRootView.setOnClickListener { onDialogRootViewClicked() }
    }

    private fun populateDialog(currentDialogInfo: DialogInfo) {
        if (currentDialogInfo.positiveLabel == null) {
            screen.dialogPositiveButton.gone()
        } else {
            screen.dialogPositiveButton.visible()
            screen.dialogPositiveButton.text = currentDialogInfo.positiveLabel
        }

        if (currentDialogInfo.negativeLabel == null) {
            screen.dialogNegativeButton.gone()
        } else {
            screen.dialogNegativeButton.visible()
            screen.dialogNegativeButton.text = currentDialogInfo.negativeLabel
        }

        if (currentDialogInfo.message == null) {
            screen.dialogMessage.gone()
        } else {
            screen.dialogMessage.visible()
            screen.dialogMessage.text = currentDialogInfo.message
        }

        screen.dialogTitle.text = currentDialogInfo.title

        if (currentDialogInfo.customViewLayoutRscId != 0) {
            customView = screen.layoutInflater.inflate(currentDialogInfo.customViewLayoutRscId!!,
                    screen.dialogCustomViewContainer, false)

            currentDialogListener?.onDialogInitCustomView(currentDialogId!!,
                    customView!!, currentDialogExtras)

            screen.dialogCustomViewContainer.removeAllViews()
            screen.dialogCustomViewContainer.addView(customView)
            screen.dialogCustomViewContainer.visible()
        } else {
            screen.dialogCustomViewContainer.gone()
        }
    }

    private fun onNegativeButtonClicked() {
        if (currentDialogListener?.onDialogNegativeButtonClicked(currentDialogId!!, customView, currentDialogExtras) == true) {
            hideDialog(false)
        }
    }

    private fun onPositiveButtonClicked() {
        if (currentDialogListener?.onDialogPositiveButtonClicked(currentDialogId!!, customView, currentDialogExtras) == true) {
            hideDialog(false)
        }
    }

    private fun onDialogRootViewClicked() {
        currentDialogListener?.onDialogRootViewClicked(currentDialogId!!, customView, currentDialogExtras)
        if(currentDialogInfo == null || currentDialogInfo?.isCancelable == true) {
            hideDialog(false)
        }
    }

    /** Public Functions **/

    fun hideDialog(fromBackPressed: Boolean) {
        if (currentDialogInfo != null && (currentDialogInfo!!.isCancelable || !fromBackPressed)) {
            screen.dialogRootView.startAnimation(hideAnimation)
            screen.dialogRootView.visibility = View.GONE
            currentDialogInfo = null
            currentDialogListener = null
            currentDialogId = null
        }
    }

    fun isShowingDialog() = currentDialogId != null

    fun showDialog(dialogId: String, dialogInfo: DialogInfo,
                   dialogListener: IDialogListener, extras: Bundle? = null) {
        screen.dialogRootView.startAnimation(showAnimation)
        screen.dialogRootView.visibility = View.VISIBLE
        currentDialogId = dialogId
        currentDialogInfo = dialogInfo
        currentDialogListener = dialogListener
        currentDialogExtras = extras
        if (currentDialogInfo != null) {
            populateDialog(currentDialogInfo!!)
        }
    }
}
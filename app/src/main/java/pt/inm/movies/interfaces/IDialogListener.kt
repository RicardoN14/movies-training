package pt.inm.movies.interfaces

import android.os.Bundle
import android.view.View

interface IDialogListener {

    /**
     * Return true if the dialog should be dismissed after user interaction
     **/
    fun onDialogPositiveButtonClicked(id: String, customView: View?, extras: Bundle? = null) = true

    /**
     * Return true if the dialog should be dismissed after user interaction
     **/
    fun onDialogNegativeButtonClicked(id: String, customView: View?, extras: Bundle? = null) = true

    fun onDialogInitCustomView(id: String, customView: View, extras: Bundle? = null) {}

    fun onDialogRootViewClicked(id: String, customView: View?, extras: Bundle? = null) {}

}
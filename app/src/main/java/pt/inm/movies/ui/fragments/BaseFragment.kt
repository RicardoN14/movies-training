package pt.inm.movies.ui.fragments

import android.app.Activity
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pt.inm.movies.ui.screens.Screen
import pt.inm.webrequests.utils.DLog

abstract class BaseFragment<S : Screen> : Fragment() {

    protected val logTag: String = javaClass.simpleName
    protected lateinit var screen: S
    protected lateinit var rootView: ViewGroup

    /**
     * Implementations must return here Fragment layout resource file
     * */
    abstract fun getLayoutResourceId(): Int

    /**
     * Implementations must use this function to
     * do the initializations (for example add listeners to views)
     *
     * This is called after the layout inflate
     */
    abstract fun doInitializations(view: View?)

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        DLog.d(logTag, "onAttach context = $context")
    }

    @Suppress("OverridingDeprecatedMember", "DEPRECATION")
    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        DLog.d(logTag, "onAttach context = $activity")
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screen = activity as S
        DLog.d(logTag, "onCreate bundle = $savedInstanceState")
    }

    /** This function can be override if implementations
     * need to do something when back button is pressed **/
    open fun onBackPressed() = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        rootView = inflater.inflate(getLayoutResourceId(), container, false) as ViewGroup
        return rootView
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DLog.d(logTag, "onViewCreated view = $view bundle = $savedInstanceState")
        doInitializations(view)
    }

    override fun onStart() {
        super.onStart()
        DLog.d(logTag, "onStart")
    }

    override fun onResume() {
        super.onResume()
        DLog.d(logTag, "onResume")
    }

    override fun onPause() {
        super.onPause()
        DLog.d(logTag, "onPause")
    }

    override fun onStop() {
        super.onStop()
        DLog.d(logTag, "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        DLog.d(logTag, "onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        DLog.d(logTag, "onDetach")
    }

}
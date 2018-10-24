package pt.inm.movies.managers

import android.view.View
import kotlinx.android.synthetic.main.error_state_view.view.*
import pt.inm.movies.extensions.gone
import pt.inm.movies.extensions.visible
import pt.inm.movies.ui.screens.Screen
import pt.inm.movies.views.ErrorStateView

class ErrorStateManager(screen: Screen, private val errorStateView: ErrorStateView,
                        private val hideViewsWhenErrorIsVisible: List<View>? = null,
                        private val onRetryButtonClickedAction: () -> Unit)
    : BaseManager<Screen>(screen) {

    private val hideViewsInitialVisibility = Array(hideViewsWhenErrorIsVisible?.size ?: 0) { 0 }
    var isShowingError = false
        private set

    init {
        addListeners()
    }

    private fun addListeners() {
        errorStateView.errorStateRetryButton.setOnClickListener { onRetryButtonClickedAction() }
    }

    fun setMessage(message: String) {
        errorStateView.errorStateMessageLabel.text = message
    }

    fun show() {
        isShowingError = true
        errorStateView.visible()
        if (hideViewsWhenErrorIsVisible != null) {
            for (i in 0 until hideViewsWhenErrorIsVisible.size) {
                val view = hideViewsWhenErrorIsVisible[i]
                hideViewsInitialVisibility[i] = view.visibility
                view.gone()
            }
        }
    }

    fun hide() {
        isShowingError = false
        errorStateView.gone()
        if (hideViewsWhenErrorIsVisible != null) {
            for (i in 0 until hideViewsWhenErrorIsVisible.size) {
                val view = hideViewsWhenErrorIsVisible[i]
                view.visibility = hideViewsInitialVisibility[i]
            }
        }
    }

}
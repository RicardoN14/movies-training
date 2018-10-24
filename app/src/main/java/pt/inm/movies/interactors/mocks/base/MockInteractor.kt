package pt.inm.movies.interactors.mocks.base

import android.os.Handler
import pt.inm.movies.entities.requests.RequestConfig
import pt.inm.movies.interactors.interfaces.base.IBaseInteractor
import pt.inm.movies.interactors.listeners.base.IInteractorListener

abstract class MockInteractor<IL : IInteractorListener> : IBaseInteractor<IL> {

    override var requestContextGroup: String? = javaClass.toString()
    override lateinit var interactorListener: IL

    protected fun executeWithDelay(runnable: () -> Unit, delay: Long = 500,
                                   requestConfig: RequestConfig?) {

        val reqConfig = requestConfig ?: RequestConfig()

        if (reqConfig.showLoader) {
            interactorListener.showLoader()
        }

        if (reqConfig.hideRootView) {
            interactorListener.hideRootView()
        }

        Handler().postDelayed({
            try {

                if (reqConfig.showLoader) {
                    interactorListener.hideLoader()
                }

                if (reqConfig.hideRootView) {
                    interactorListener.showRootView()
                }


                runnable()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }, delay)
    }

    override fun cancelAllRunningRequests() {
        // TODO
    }

    override fun retryFailedRequests() {
        // TODO
    }
}
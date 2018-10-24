package pt.inm.movies.entities.requests

/**
 * Created by ricardo.neves on 3/27/2018.
 *
 * showLoader: If true, the loader is visible when request is running
 * showError: If true, handle error is done. If false the error is never shown to the user.
 * showErrorStateView: If true the error (if showError = true) is shown in layout with retry button
 *     If false is shown in dialog
 * requestId: Id for the request if needed
 * hideRootView: If true hide the rootView when the request is running
 */
class RequestConfig(var showLoader: Boolean = true,
                    var showError: Boolean = true,
                    var showErrorStateView: Boolean = false,
                    var requestId: String? = null,
                    var hideRootView: Boolean = false)
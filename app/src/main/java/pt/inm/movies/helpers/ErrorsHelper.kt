package pt.inm.movies.helpers

import pt.inm.movies.R
import pt.inm.movies.entities.dialogs.DialogInfo
import pt.inm.movies.entities.requests.RequestConfig
import pt.inm.movies.entities.requests.RequestError
import pt.inm.movies.entities.requests.RequestErrorType
import pt.inm.movies.http.entities.responses.HeaderResponseData
import pt.inm.movies.managers.ErrorStateManager
import pt.inm.movies.ui.screens.ExecuteRequestScreen
import pt.inm.movies.ui.screens.Screen

fun handleError(screen: Screen, requestConfig: RequestConfig,
                requestError: RequestError, errorStateManager: ErrorStateManager) {

    if (!requestConfig.showError) {
        return
    }

    val message = when (requestError.requestErrorType) {
        RequestErrorType.SERVER_ERROR -> {

            val headerResponseData = requestError.headerResponseData

            if (headerResponseData != null && tryHandleSpecificErrorCode(screen,
                            requestError.headerResponseData)) {
                return
            }

            getErrorMessage(headerResponseData, screen)
        }

        RequestErrorType.TIMEOUT_ERROR -> {
            screen.getString(R.string.error_timeout_message)
        }

        RequestErrorType.PARSE_ERROR -> {
            screen.getString(R.string.error_parse_message)
        }

        RequestErrorType.SSL_ERROR -> {
            screen.getString(R.string.error_ssl_connection_message)
        }

        else -> /* RequestErrorType.INTERNET_CONNECTION_ERROR */ {
            screen.getString(R.string.error_internet_connection_message)
        }
    }

    handleError(screen, requestConfig, message, errorStateManager)
}

private fun getErrorMessage(headerResponseData: HeaderResponseData?, screen: Screen): String {
    return ""
//    TODO: Return error message
//    TODO: Example:
//    val message = headerResponseData?.message
//            ?: screen.getString(R.string.error_server_message)
//    return if (message.isEmpty()) {
//        screen.getString(R.string.error_server_with_empty_message)
//    } else {
//        message
//    }
}

/** Return true if specific error code was consumed **/
fun tryHandleSpecificErrorCode(screen: Screen, headerResponseData: HeaderResponseData): Boolean {
    return false
}

fun showDialogError(screen: Screen, message: String?,
                    dialogId: String, isCancelable: Boolean = true) {
    screen.showDialog(dialogId, DialogInfo(
            positiveLabel = screen.getString(R.string.error_dialog_ok),
            title = screen.getString(R.string.error_dialog_title),
            message = message,
            isCancelable = isCancelable))
}

fun handleError(screen: Screen, requestConfig: RequestConfig,
                message: String, errorStateManager: ErrorStateManager) {
    if (requestConfig.showErrorStateView) {
        errorStateManager.setMessage(message)
        errorStateManager.show()
    } else {
        showDialogError(screen, message, ExecuteRequestScreen.ERROR_DIALOG_ID)
    }
}
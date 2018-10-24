package pt.inm.movies.extensions

import pt.inm.movies.http.entities.qstringargs.QueryStringArgs
import pt.inm.webrequests.utils.DLog
import java.lang.reflect.Modifier

const val TAG_STRING_BUILDER_EXTENSIONS = "StringBuilderExtensions"

fun StringBuilder.addQueryParam(key: String, value: String): StringBuilder {
    return append(if (indexOf("?") >= 0) "&" else "?").append(key).append("=").append(value.encodeUTF8())
}

fun <T : QueryStringArgs> StringBuilder.addQueryParams(queryStringEntity: T?): StringBuilder {
    if (queryStringEntity == null) {
        return this
    }

    val fields = queryStringEntity.javaClass.declaredFields
    for (field in fields) {
        field.isAccessible = true
        if (Modifier.isStatic(field.modifiers)) {
            continue
        }
        try {
            val objectValue = field.get(queryStringEntity)
            val fieldName = field.name
            // Only the fields with String type are added to Query String
            if (objectValue is String) {
                addQueryParam(fieldName, objectValue)
            } else if (objectValue is List<*>) {
                for (obj in objectValue) {
                    if (obj is String) {
                        // Only can handle for now List<String>
                        addQueryParam(fieldName, obj)
                    }
                }
            }
        } catch (e: IllegalAccessException) {
            DLog.e(TAG_STRING_BUILDER_EXTENSIONS, "addQueryParams exception : $e")
        }

    }

    return this
}
package pt.inm.movies.extensions

import pt.inm.webrequests.utils.DLog
import java.io.UnsupportedEncodingException
import java.math.BigDecimal
import java.net.URLEncoder
import java.text.Normalizer
import java.text.SimpleDateFormat
import java.util.*

/* Email pattern: aaa.a@aaa.aa */
const val EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
const val TAG_STRING_EXTENSIONS = "StringExtensions"
const val UTF_8 = "UTF-8"

/* email validation*/
fun String.isEmailValid(): Boolean {
    val pattern = java.util.regex.Pattern.compile(EMAIL_PATTERN)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String?.isNullOrEmptyAfterTrim() = if (this == null) true else trim().isEmpty()

fun String.removeNonAsciiLetters(): String {
    val asciiText = Normalizer.normalize(this, Normalizer.Form.NFD)
            .replace("[^\\p{ASCII}]".toRegex(), "")

    return asciiText.replace("[^A-Za-z0-9,.\\-():+?\\s'/]".toRegex(), "")
}

fun String.removeSpaces(): String {
    return this.replace(" ", "")
}

fun String.toFormattedAmount(): String {
    val value = toBigDecimal()

    if (value.toFloat() == 0f) {
        return ""
    }

    return value.formatCurrencyFromBigDecimal()
}

fun String.isValidPhoneNumber(): Boolean {
    return (this.length in 6..15) && android.util.Patterns.PHONE.matcher(this).matches()
}

fun String.toBigDecimal(): BigDecimal {
    return if (this.isEmpty()) {
        BigDecimal(0)
    } else {
        val cleanNumber = this.cleanNumber()
        BigDecimal(cleanNumber).divide(BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP)
    }
}

fun String.cleanNumber() = replace("[^0-9-]".toRegex(), "")

fun String.toDate(formatDateIsIn: String) = this.toDate(SimpleDateFormat(formatDateIsIn, Locale.getDefault()))

fun String.toDate(formatDateIsIn: SimpleDateFormat): Date? {
    var date: Date? = null
    try {
        date = formatDateIsIn.parse(this)
    } catch (e: Exception) {
        DLog.e(TAG_STRING_EXTENSIONS, "toDate() exception: $e")
    }
    return date
}

fun String?.encodeUTF8(): String? {
    if (this == null) {
        return null
    }

    return try {
        URLEncoder.encode(this, UTF_8)
    } catch (e: UnsupportedEncodingException) {
        DLog.e(TAG_STRING_EXTENSIONS, "encodeUTF8() error encoding to UTF-8: " + e.message)
        ""
    }

}
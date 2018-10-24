package pt.inm.movies.extensions

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

val decimalFormat = DecimalFormat("##,##0.00", DecimalFormatSymbols(Locale("pt", "PT")))

fun BigDecimal.toFormattedValue(currency: String? = null, withSignal: Boolean = false): String {
    var formatted = decimalFormat.format(this)
    formatted = formatted.replace(160.toChar(), '.')
    formatted += if (currency?.isNotEmpty() == true) currency else ""

    if (!withSignal || this.signum() == -1) {
        return formatted
    }

    return "+ $formatted"
}

fun BigDecimal.formatCurrencyFromBigDecimal(): String {
    val symbols = DecimalFormatSymbols(Locale.getDefault())
    symbols.decimalSeparator = ','
    symbols.groupingSeparator = '.'
    val decimalFormat = DecimalFormat("#,0.00")
    decimalFormat.decimalFormatSymbols = symbols
    decimalFormat.groupingSize = 3

    return decimalFormat.format(this)
}
package pt.inm.movies.helpers

import pt.inm.movies.BuildConfig

const val QUA_FLAVOR = "qua"
const val DEV_FLAVOR = "dev"
const val MOCK_FLAVOR = "mock"

fun isFlavor(flavor: String) = BuildConfig.FLAVOR.trim().startsWith(flavor)

val isQuaFlavor: Boolean
    get() = isFlavor(QUA_FLAVOR)

val isDevFlavor: Boolean
    get() = isFlavor(DEV_FLAVOR)

val isMockFlavor: Boolean
    get() = isFlavor(MOCK_FLAVOR)


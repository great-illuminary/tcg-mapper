package eu.codlab.tcgmapper

import kotlinx.serialization.Serializable

@Serializable
data class TranslationHolder<T>(
    val en: T,
    val fr: T? = null,
    val de: T? = null,
    val it: T? = null,
    val es: T? = null
)

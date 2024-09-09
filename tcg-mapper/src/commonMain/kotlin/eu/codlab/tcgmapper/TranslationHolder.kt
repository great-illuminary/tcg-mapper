package eu.codlab.tcgmapper

import kotlinx.serialization.Serializable

@Serializable
data class TranslationHolder(
    val en: String,
    val fr: String? = null,
    val de: String? = null,
    val it: String? = null,
    val es: String? = null
)

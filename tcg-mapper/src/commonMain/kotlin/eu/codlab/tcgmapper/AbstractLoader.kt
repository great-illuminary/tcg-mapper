package eu.codlab.tcgmapper

import kotlinx.serialization.KSerializer
import kotlinx.serialization.StringFormat

open class AbstractLoader<T>(
    file: String,
    serializer: KSerializer<T>,
    github: GithubConfiguration,
    fileResource: suspend () -> ByteArray,
) {
    private val loader: Loader<T> = Loader(
        file,
        serializer,
        github,
        fileResource,
    )

    suspend fun loadFromGithub(tag: String = "main") = loader.loadFromGithub(tag)

    suspend fun loadFromResource() = loader.loadFromResource()

    fun to(values: T, encoder: StringFormat = Provider.json) = loader.to(values, encoder)
}

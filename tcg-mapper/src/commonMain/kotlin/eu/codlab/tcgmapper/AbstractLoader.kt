package eu.codlab.tcgmapper

import dev.icerock.moko.resources.FileResource
import kotlinx.serialization.KSerializer
import kotlinx.serialization.StringFormat

data class AbstractLoader<T>(
    private val fileResource: FileResource,
    private val file: String,
    private val serializer: KSerializer<T>,
    private val githubGroup: String = GithubConfiguration.githubGroup,
    private val githubRepo: String = GithubConfiguration.githubRepo
) {

    private val loader: Loader<T> = Loader(
        fileResource,
        file,
        serializer,
        githubGroup,
        githubRepo
    )

    suspend fun loadFromGithub(tag: String = "main") = loader.loadFromGithub(tag)

    suspend fun loadFromResource() = loader.loadFromResource()

    fun to(values: T, encoder: StringFormat = Provider.json) = loader.to(values, encoder)
}

package eu.codlab.tcgmapper

import dev.icerock.moko.resources.FileResource
import kotlinx.serialization.KSerializer
import kotlinx.serialization.StringFormat

open class AbstractLoader<T>(
    fileResource: FileResource,
    file: String,
    serializer: KSerializer<T>,
    githubGroup: String = GithubConfiguration.githubGroup,
    githubRepo: String = GithubConfiguration.githubRepo
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

package eu.codlab.tcgmapper

import dev.icerock.moko.resources.FileResource
import eu.codlab.moko.ext.safelyReadContent
import eu.codlab.tcgmapper.GithubDefinitions.dataFileContent
import kotlinx.serialization.KSerializer
import kotlinx.serialization.StringFormat

data class Loader<T>(
    private val fileResource: FileResource,
    private val file: String,
    private val serializer: KSerializer<T>,
    private val githubGroup: String = GithubConfiguration.githubGroup,
    private val githubRepo: String = GithubConfiguration.githubRepo
) {

    suspend fun loadFromGithub(tag: String = "main"): T {
        return Provider.yaml.decodeFromString(
            serializer,
            dataFileContent(
                GithubConfiguration.githubGroup,
                GithubConfiguration.githubRepo,
                tag,
                file
            )
        )
    }

    suspend fun loadFromResource(): T {
        return Provider.yaml.decodeFromString(serializer, fileResource.safelyReadContent())
    }

    fun to(values: T, encoder: StringFormat = Provider.json): String {
        return encoder.encodeToString(serializer, values)
    }
}

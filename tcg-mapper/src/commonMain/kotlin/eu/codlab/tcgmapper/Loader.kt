package eu.codlab.tcgmapper

import eu.codlab.tcgmapper.GithubDefinitions.dataFileContent
import kotlinx.serialization.KSerializer
import kotlinx.serialization.StringFormat

data class Loader<T>(
    private val file: String,
    private val serializer: KSerializer<T>,
    private val github: GithubConfiguration,
    private val fileResource: suspend () -> ByteArray,
) {
    suspend fun loadFromGithub(tag: String = "main"): T {
        return Provider.yaml.decodeFromString(
            serializer,
            dataFileContent(
                github.githubGroup,
                github.githubRepo,
                tag,
                file
            )
        )
    }

    suspend fun loadFromResource(): T {
        val file = fileResource()

        return Provider.yaml.decodeFromString(
            serializer,
            io.ktor.utils.io.core.String(file)
        )
    }

    fun to(values: T, encoder: StringFormat = Provider.json): String {
        return encoder.encodeToString(serializer, values)
    }
}

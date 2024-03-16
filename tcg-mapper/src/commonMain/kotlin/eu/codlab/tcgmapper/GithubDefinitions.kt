package eu.codlab.tcgmapper

import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import korlibs.io.lang.FileNotFoundException

internal object GithubDefinitions {
    suspend fun dataFileContent(
        githubGroup: String,
        githubRepo: String,
        version: String,
        file: String
    ): String {
        val prefix = "https://raw.githubusercontent.com"
        val url = "$prefix/$githubGroup/$githubRepo/$version/data/$file.yml"

        val request = Provider.client.get(url)

        if (!request.status.isSuccess()) {
            throw FileNotFoundException("Impossible to find the file $file for version $version")
        }

        return request.bodyAsText()
    }
}

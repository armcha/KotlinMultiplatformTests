package data.api

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.http.URLProtocol
import domain.models.response.TraktTvIdsResponse
import domain.models.response.TraktTvResponse
import domain.models.response.OmdbResponse
import domain.models.response.Rating

internal abstract class CommonApiManager(private val endPoint: String) {

    protected val httpClient = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer().apply {
                setMapper(TraktTvResponse::class, TraktTvResponse.serializer())
                setMapper(TraktTvIdsResponse::class, TraktTvIdsResponse.serializer())
                setMapper(OmdbResponse::class, OmdbResponse.serializer())
                setMapper(Rating::class, Rating.serializer())
            }
        }
        install(ExpectSuccess)
    }

    protected fun HttpRequestBuilder.apiUrl(path: String = "") {
        withHeaders().forEach {
            header(it.first, it.second)
        }
        url {
            protocol = URLProtocol.HTTPS
            host = endPoint
            encodedPath = path
        }
    }

    open fun withHeaders() = listOf<Pair<String, String>>()
}
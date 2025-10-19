package arya.projects.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 * An OkHttp [Interceptor] that adds an API key to the query parameters of every request.
 *
 * This interceptor is used to automatically append the `api_key` query parameter to the URL
 * of each outgoing HTTP request, simplifying the process of authenticating with the API.
 *
 * @param apiKey The API key to be added to the requests.
 */
class ApiKeyInterceptor(private val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val url = original.url.newBuilder()
            .addQueryParameter("api_key", apiKey)
            .build()
        val request = original.newBuilder().url(url).build()
        return chain.proceed(request)
    }

}
package me.vlasoff.appselecttz.data.network.apiservice

import me.vlasoff.appselecttz.utils.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

// добавил интерсептор, теперь ключик тут
class MoviesInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url().newBuilder().addQueryParameter("api-key", API_KEY).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}
package me.akay.movies.network

import me.akay.movies.BuildConfig
import me.akay.movies.helper.MD5Util
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class AppInterceptor : Interceptor {
    private var timeString: String = System.currentTimeMillis().toString()
    private var hash: String = MD5Util.md5(timeString + BuildConfig.PRIVATE_KEY + BuildConfig.PUBLIC_KEY)

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val originalHttpUrl: HttpUrl = request.url

        val url: HttpUrl = originalHttpUrl.newBuilder()
            .addQueryParameter("ts", timeString)
            .addQueryParameter("apikey", BuildConfig.PUBLIC_KEY)
            .addQueryParameter("hash", hash)
            .build()

        val builder: Request.Builder = request.newBuilder()
            .header("Content-Type", "application/json")
            .method(request.method, request.body)
            .url(url)


        request = builder.build()
        return chain.proceed(request)
    }
}
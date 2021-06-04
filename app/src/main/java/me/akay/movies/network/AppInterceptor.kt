package me.akay.movies.network

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class AppInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val originalHttpUrl: HttpUrl = request.url

        val url: HttpUrl = originalHttpUrl.newBuilder()
            .addQueryParameter("ts", "1622732471")
            .addQueryParameter("apikey", "463ad30f7f54e2c99f697ce0a7e5a00f")
            .addQueryParameter("hash", "bbc278bdd02952e17d40d8a158357944")
            .build()

        val builder: Request.Builder = request.newBuilder()
            .header("Content-Type", "application/json")
            .method(request.method, request.body)
            .url(url)


        request = builder.build()
        return chain.proceed(request)
    }
}
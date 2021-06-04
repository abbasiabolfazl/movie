package me.akay.movies.network

import io.reactivex.Single
import me.akay.movies.datamodels.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/v1/public/characters?orderBy=name")
    fun getCharacterList(
        @Query("limit") limit: Int, @Query("offset") offset: Int
    ): Single<CharacterResponse>
}
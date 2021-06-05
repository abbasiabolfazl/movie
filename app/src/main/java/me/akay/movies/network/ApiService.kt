package me.akay.movies.network

import io.reactivex.Single
import me.akay.movies.datamodels.CharacterResponse
import me.akay.movies.datamodels.ComicResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/v1/public/characters?orderBy=name")
    fun getCharacterList(
        @Query("limit") limit: Int, @Query("offset") offset: Int
    ): Single<CharacterResponse>


    @GET("/v1/public/characters/{id}/comics?format=comic&formatType=comic&noVariants=true")
    fun getCharacterComics(
        @Path("id") id: Int,
        @Query("limit") limit: Int, @Query("offset") offset: Int,
        @Query("dateRange") startRange: String, @Query("dateRange") endRange: String
    ): Single<ComicResponse>
}
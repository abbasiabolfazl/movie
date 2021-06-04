package me.akay.movies.characterList

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import me.akay.movies.datamodels.Character
import me.akay.movies.network.ApiService
import javax.inject.Inject

class CharacterRepository @Inject constructor(private val apiService: ApiService) {

    fun getCharacterList(offset: Int, limit: Int): Single<List<Character>?> {
        return apiService.getCharacterList(limit, offset)
            .subscribeOn(Schedulers.io())
            .map { data -> return@map data.data?.results }
    }
}
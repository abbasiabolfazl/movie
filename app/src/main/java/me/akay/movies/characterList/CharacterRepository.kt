package me.akay.movies.characterList

import androidx.lifecycle.MutableLiveData
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import me.akay.movies.datamodels.Character
import me.akay.movies.datamodels.Comic
import me.akay.movies.helper.Resource
import me.akay.movies.network.ApiService
import javax.inject.Inject

class CharacterRepository @Inject constructor(private val apiService: ApiService) {

    fun getCharacterList(offset: Int, limit: Int): Single<List<Character>?> {
        return apiService.getCharacterList(limit, offset)
            .subscribeOn(Schedulers.io())
            .map { data -> return@map data.data?.results }
    }

    fun getCharacter(
        result: MutableLiveData<Resource<List<Comic>>>,
        id: Int, start: String, end: String, skip: Int = 0, limit: Int = 10
    ): Disposable {
        return apiService.getCharacterComics(id, limit, skip, start, end)
            .subscribeOn(Schedulers.io())
            .map { data -> return@map data.data?.results }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                if (response != null) {
                    result.value = Resource.success(response)
                }
            }, { e -> e.printStackTrace() })
    }


}
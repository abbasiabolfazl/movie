package me.akay.movies.character

import androidx.lifecycle.MutableLiveData
import me.akay.movies.characterList.CharacterRepository
import me.akay.movies.datamodels.Comic
import me.akay.movies.dependencyInjection.BaseViewModel
import me.akay.movies.helper.Resource
import javax.inject.Inject

class CharacterViewModel @Inject constructor(private val repository: CharacterRepository) : BaseViewModel() {

    val liveData: MutableLiveData<Resource<List<Comic>>> by lazy {
        MutableLiveData<Resource<List<Comic>>>()
    }

    fun getCharacterComics(id: Int) {
        addDisposable(repository.getCharacter(liveData, id, "2005-01-01", "2013-01-01"))
    }
}
package me.akay.movies.characterList

import android.view.View
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.processors.PublishProcessor
import me.akay.movies.datamodels.Character
import me.akay.movies.dependencyInjection.BaseViewModel
import me.akay.movies.helper.Resource
import java.util.*
import javax.inject.Inject

class CharacterViewModel @Inject constructor(private val characterRepository: CharacterRepository) : BaseViewModel() {
    private val pagination = PublishProcessor.create<Int>()
    private var page = 0

    private companion object {
        const val LIMIT = 30
    }

    val characterLiveData: MutableLiveData<Resource<List<Character>>> by lazy {
        MutableLiveData<Resource<List<Character>>>()
    }

    val loadingLiveData: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val loadMoreLiveData: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    init {
        val disposable = pagination.onBackpressureDrop()
            .doOnNext { getLoadingProgress().postValue(View.VISIBLE) }
            .concatMapSingle<List<Character>> { characterRepository.getCharacterList(page * LIMIT, LIMIT) }
            .doOnError { throwable: Throwable? ->
                characterLiveData.postValue(Resource.error(throwable?.cause.toString(), null))
                getLoadingProgress().postValue(View.GONE)
            }
            .onErrorReturn { ArrayList<Character>() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { characterList ->
                characterLiveData.value = Resource.success(characterList)
                getLoadingProgress().postValue(View.GONE)
            }

        addDisposable(disposable)
        pagination.onNext(page)
    }

    private fun getLoadingProgress(): MutableLiveData<Int> {
        return if (page == 0) {
            loadingLiveData
        } else {
            loadMoreLiveData
        }
    }

    fun onPageEnded() {
        page++
        pagination.onNext(page)
    }
}
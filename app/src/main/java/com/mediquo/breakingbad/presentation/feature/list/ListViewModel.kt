package com.mediquo.breakingbad.presentation.feature.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mediquo.breakingbad.domain.interactor.GetCharacters
import com.mediquo.breakingbad.domain.model.Character
import com.mediquo.breakingbad.presentation.common.Resource
import com.mediquo.breakingbad.presentation.executor.InteractorExecutor

class ListViewModel(
    private val executor: InteractorExecutor,
    private val getCharacters: GetCharacters
) : ViewModel() {

    private val _characters = MutableLiveData<Resource<List<Character>>>()

    val characters: LiveData<Resource<List<Character>>>
        get() = _characters

    fun loadCharacters() {
        _characters.value = Resource.loading()
        executor(
            interactor = getCharacters,
            request = Unit,
            onError = {
                _characters.value = Resource.error(it)
            },
            onSuccess = {
                _characters.value = Resource.success(it)
            }
        )
    }
}

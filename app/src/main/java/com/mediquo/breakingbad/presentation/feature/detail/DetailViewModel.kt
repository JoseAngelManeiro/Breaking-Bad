package com.mediquo.breakingbad.presentation.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mediquo.breakingbad.domain.interactor.GetCharacterDetail
import com.mediquo.breakingbad.presentation.common.Resource
import com.mediquo.breakingbad.presentation.executor.InteractorExecutor

class DetailViewModel(
    private val executor: InteractorExecutor,
    private val getCharacterDetail: GetCharacterDetail,
    private val factory: DetailModelFactory
) : ViewModel() {

    private val _detailModel = MutableLiveData<Resource<DetailModel>>()

    val detailModel: LiveData<Resource<DetailModel>>
        get() = _detailModel

    fun loadDetailModel(name: String) {
        _detailModel.value = Resource.loading()
        executor(
            interactor = getCharacterDetail,
            request = name,
            onError = {
                _detailModel.value = Resource.error(it)
            },
            onSuccess = {
                _detailModel.value = Resource.success(
                    factory.createDetailModel(it.character, it.quotes))
            }
        )
    }
}

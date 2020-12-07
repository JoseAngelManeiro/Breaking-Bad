package com.mediquo.breakingbad.presentation.feature.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mediquo.breakingbad.domain.Either
import com.mediquo.breakingbad.domain.interactor.GetCharacterDetail
import com.mediquo.breakingbad.domain.model.Character
import com.mediquo.breakingbad.domain.model.Quote
import com.mediquo.breakingbad.presentation.common.Resource
import com.mediquo.breakingbad.presentation.executor.SyncInteractorExecutor
import com.mediquo.breakingbad.util.mock
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.BDDMockito.given
import org.mockito.Mockito.verify
import java.lang.Exception

class DetailViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val executor = SyncInteractorExecutor()
    private val getCharacterDetail = mock<GetCharacterDetail>()
    private val factory = mock<DetailModelFactory>()
    private val observer = mock<Observer<Resource<DetailModel>>>()

    private lateinit var viewModel: DetailViewModel

    @Before
    fun setUp() {
        viewModel = DetailViewModel(executor, getCharacterDetail, factory)
        viewModel.detailModel.observeForever(observer)
    }

    @Test
    fun `loads the expected detail model after invokes factory`() {
        val name = "Walter White"
        val character = mock<Character>()
        val quotes = listOf<Quote>(mock())
        val detailModel = mock<DetailModel>()
        given(getCharacterDetail(name))
            .willReturn(Either.right(GetCharacterDetail.Response(character, quotes)))
        given(factory.createDetailModel(character, quotes))
            .willReturn(detailModel)

        viewModel.loadDetailModel(name)

        verify(observer).onChanged(Resource.loading())
        verify(observer).onChanged(Resource.success(detailModel))
    }

    @Test
    fun `if something goes wrong, it returns the exception received`() {
        val name = "Walter White"
        val exception = Exception("Any exception")
        given(getCharacterDetail(name)).willReturn(Either.left(exception))

        viewModel.loadDetailModel(name)

        verify(observer).onChanged(Resource.loading())
        verify(observer).onChanged(Resource.error(exception))
    }
}
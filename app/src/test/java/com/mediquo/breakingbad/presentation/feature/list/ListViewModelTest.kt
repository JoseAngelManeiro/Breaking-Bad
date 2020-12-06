package com.mediquo.breakingbad.presentation.feature.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mediquo.breakingbad.domain.Either
import com.mediquo.breakingbad.domain.interactor.GetCharacters
import com.mediquo.breakingbad.domain.model.Character
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

class ListViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val executor = SyncInteractorExecutor()
    private val getCharacters = mock<GetCharacters>()
    private val observer = mock<Observer<Resource<List<Character>>>>()

    private lateinit var viewModel: ListViewModel

    @Before
    fun setUp() {
        viewModel = ListViewModel(executor, getCharacters)
        viewModel.characters.observeForever(observer)
    }

    @Test
    fun `loads the expected list of characters`() {
        val characters = listOf<Character>(mock())
        given(getCharacters(Unit)).willReturn(Either.right(characters))

        viewModel.loadCharacters()

        verify(observer).onChanged(Resource.loading())
        verify(observer).onChanged(Resource.success(characters))
    }

    @Test
    fun `if something goes wrong, it returns the exception received`() {
        val exception = Exception("Any exception")
        given(getCharacters(Unit)).willReturn(Either.left(exception))

        viewModel.loadCharacters()

        verify(observer).onChanged(Resource.loading())
        verify(observer).onChanged(Resource.error(exception))
    }
}

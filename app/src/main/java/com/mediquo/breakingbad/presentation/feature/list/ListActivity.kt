package com.mediquo.breakingbad.presentation.feature.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import com.mediquo.breakingbad.R
import com.mediquo.breakingbad.domain.model.Character
import com.mediquo.breakingbad.presentation.common.goToDetail
import com.mediquo.breakingbad.presentation.common.handleException
import kotlinx.android.synthetic.main.activity_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class ListActivity : AppCompatActivity() {

  private val viewModel: ListViewModel by viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_list)

    viewModel.characters.observe(this, { resource ->
      resource.fold(
        onLoading = {
          progress_bar.visibility = View.VISIBLE
        },
        onError = {
          progress_bar.visibility = View.GONE
          handleException(it) { viewModel.loadCharacters() }
        },
        onSuccess = {
          progress_bar.visibility = View.GONE
          setUpRecyclerView(it)
        }
      )
    })

    if (savedInstanceState == null) {
      viewModel.loadCharacters()
    }
  }

  private fun setUpRecyclerView(characters: List<Character>) {
    val headers = listOf("Breaking Bad", "Better Call Saul")
    val recyclerItems = mutableListOf<RecyclerItem>()
    headers.forEach { header ->
      recyclerItems.add(RecyclerItem.Header(header))
      recyclerItems.addAll(characters.filter { it.category.contains(header) }
        .map { RecyclerItem.Item(it) })
    }
    recycler_view.adapter = CharactersAdapter(
      items = recyclerItems,
      onItemClickListener = { goToDetail(it.name) }
    )
    recycler_view.addItemDecoration(
      DividerItemDecoration(recycler_view.context, LinearLayout.VERTICAL))
  }
}
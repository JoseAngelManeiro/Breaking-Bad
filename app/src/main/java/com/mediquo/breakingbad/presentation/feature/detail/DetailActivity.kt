package com.mediquo.breakingbad.presentation.feature.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.mediquo.breakingbad.R
import com.mediquo.breakingbad.presentation.common.handleException
import com.mediquo.breakingbad.presentation.common.yearsUntilNow
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

  companion object {
    const val DETAIL_ARGS_NAME_KEY = "detail_args_name_key"
  }

  private val viewModel: DetailViewModel by viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_detail)

    val name = intent.getStringExtra(DETAIL_ARGS_NAME_KEY)
      ?: throw IllegalArgumentException("Arguments not found: " + this::class.java.simpleName)

    supportActionBar?.apply {
      this.title = name
      this.setDisplayHomeAsUpEnabled(true)
    }

    viewModel.detailModel.observe(this, { resource ->
      resource.fold(
        onLoading = {
          detail_container.visibility = View.GONE
          progress_bar.visibility = View.VISIBLE
        },
        onError = {
          progress_bar.visibility = View.GONE
          handleException(it) { viewModel.loadDetailModel(name) }
        },
        onSuccess = {
          progress_bar.visibility = View.GONE
          detail_container.visibility = View.VISIBLE
          setUpView(it)
        }
      )
    })

    if (savedInstanceState == null) {
      viewModel.loadDetailModel(name)
    }
  }

  private fun setUpView(detailModel: DetailModel) {
    Picasso.get()
      .load(detailModel.imgUrl)
      .placeholder(R.drawable.ic_avatar_placeholder)
      .error(R.drawable.ic_avatar_placeholder)
      .into(photo_image_view)

    name_value_text_view.text = detailModel.name

    if (detailModel.birthday != null) {
      age_value_text_view.text = detailModel.birthday.yearsUntilNow().toString()
    } else {
      age_label_text_view.visibility = View.GONE
      age_value_text_view.visibility = View.GONE
    }

    if (!detailModel.breakingBadAppearance.isNullOrEmpty()) {
      breaking_bad_value_text_view.text = detailModel.breakingBadAppearance.formatSeasons()
    } else {
      breaking_bad_label_text_view.visibility = View.GONE
      breaking_bad_value_text_view.visibility = View.GONE
    }

    if (!detailModel.betterCallSaulAppearance.isNullOrEmpty()) {
      better_call_saul_value_text_view.text = detailModel.betterCallSaulAppearance.formatSeasons()
    } else {
      better_call_saul_label_text_view.visibility = View.GONE
      better_call_saul_value_text_view.visibility = View.GONE
    }

    if (detailModel.quotes.isNotEmpty()) {
      quotes_recycler_view.adapter = QuotesAdapter(items = detailModel.quotes)
    } else{
      quotes_label_text_view.visibility = View.GONE
      quotes_recycler_view.visibility = View.GONE
    }
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    val id = item.itemId
    if (id == android.R.id.home) {
      onBackPressed()
      return true
    }
    return super.onOptionsItemSelected(item)
  }

  private fun List<Int>.formatSeasons(): String {
    return this.toString().replace("[", "").replace("]", "")
  }
}
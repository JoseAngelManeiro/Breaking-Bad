package com.mediquo.breakingbad.presentation.common

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.mediquo.breakingbad.presentation.feature.detail.DetailActivity

fun AppCompatActivity.goToDetail(name: String) {
    val intent = Intent(this, DetailActivity::class.java).apply {
        putExtra(DetailActivity.DETAIL_ARGS_NAME_KEY, name)
    }
    startActivity(intent)
}
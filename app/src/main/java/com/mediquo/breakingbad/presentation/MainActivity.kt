package com.mediquo.breakingbad.presentation

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mediquo.breakingbad.R
import com.mediquo.breakingbad.domain.interactor.GetCharacterDetail
import com.mediquo.breakingbad.domain.interactor.GetCharacters
import com.mediquo.breakingbad.domain.repository.CharacterRepository
import com.mediquo.breakingbad.domain.repository.QuoteRepository
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

  private val characterRepository: CharacterRepository by inject()
  private val quoteRepository: QuoteRepository by inject()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    CharactersTask(characterRepository, quoteRepository).execute()
  }

  class CharactersTask(
    private val characterRepository: CharacterRepository,
    private val quoteRepository: QuoteRepository
  ) : AsyncTask<Void, Void, String>() {
    override fun doInBackground(vararg params: Void?): String? {
      val getCharacters = GetCharacters(characterRepository)
      val getCharacterDetail = GetCharacterDetail(characterRepository, quoteRepository)
      val total = getCharacters(Unit)
      val detail = getCharacterDetail("Walter White")
      return ""
    }
    override fun onPostExecute(result: String?) {
      val r = result
    }
  }

}
package com.mediquo.breakingbad.presentation.feature.detail

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mediquo.breakingbad.R
import com.mediquo.breakingbad.presentation.common.inflate

class QuotesAdapter(
  private val items: List<String>
) : RecyclerView.Adapter<QuotesAdapter.ItemViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
    val itemViewHolder = parent.inflate(R.layout.item_quote)
    return ItemViewHolder(itemViewHolder)
  }

  override fun getItemCount() = items.size

  override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
    holder.bind(items[position])
  }

  inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val quoteTextView = itemView.findViewById(R.id.quote_text_view) as TextView

    fun bind(quote: String) {
      quoteTextView.text = quote
    }
  }
}

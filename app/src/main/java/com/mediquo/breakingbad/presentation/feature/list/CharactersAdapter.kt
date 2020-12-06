package com.mediquo.breakingbad.presentation.feature.list

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mediquo.breakingbad.R
import com.mediquo.breakingbad.domain.model.Character
import com.mediquo.breakingbad.presentation.common.PicassoCircleTransformation
import com.mediquo.breakingbad.presentation.common.inflate
import com.squareup.picasso.Picasso

class CharactersAdapter(
  private val items: List<RecyclerItem>,
  private val onItemClickListener: (Character) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  private val TYPE_HEADER = 0
  private val TYPE_ITEM = 1

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return if (viewType == TYPE_HEADER) {
      HeaderViewHolder(parent.inflate(R.layout.item_header))
    } else {
      CharacterViewHolder(parent.inflate(R.layout.item_character))
    }
  }

  override fun getItemCount() = items.size

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    when(val item = items[holder.adapterPosition]) {
      is RecyclerItem.Header -> (holder as HeaderViewHolder).bind(item.title)
      is RecyclerItem.Item -> (holder as CharacterViewHolder).bind(item.character)
    }
  }

  override fun getItemViewType(position: Int): Int {
    return if (items[position] is RecyclerItem.Header) {
      TYPE_HEADER
    } else {
      TYPE_ITEM
    }
  }

  inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val headerTextView = itemView.findViewById(R.id.header_text_view) as TextView

    fun bind(title: String) {
      headerTextView.text = title
    }
  }

  inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val photoImageView = itemView.findViewById(R.id.photo_image_view) as ImageView
    private val nickTextView = itemView.findViewById(R.id.nick_text_view) as TextView
    private val nameTextView = itemView.findViewById(R.id.name_text_view) as TextView

    init {
      itemView.setOnClickListener {
        val character = (items[adapterPosition] as RecyclerItem.Item).character
        onItemClickListener(character)
      }
    }

    fun bind(character: Character) {
      Picasso.get()
        .load(character.img)
        .placeholder(R.drawable.ic_avatar_placeholder)
        .error(R.drawable.ic_avatar_placeholder)
        .transform(PicassoCircleTransformation())
        .into(photoImageView)

      nickTextView.text = character.nickname
      nameTextView.text = character.name
    }
  }
}

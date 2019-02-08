package fr.hetic.h3p2021

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IItem
import com.mikepenz.fastadapter.items.AbstractItem

class PokemonViewHolder(itemView: View): FastAdapter.ViewHolder<PokemonItem>(itemView) {

    val nameTextView: TextView
    val levelTextView: TextView
    val imageView: ImageView

    init {
        nameTextView = itemView.findViewById(R.id.nameTextView)
        levelTextView = itemView.findViewById(R.id.levelTextView)
        imageView = itemView.findViewById(R.id.imageView)
    }

    override fun bindView(item: PokemonItem, payloads: MutableList<Any>) {
        nameTextView.text = item.name

        val level = item.level
        levelTextView.text = "$level"

        Glide.with(imageView)
            .load(item.imageUrl)
            .into(imageView)


    }

    override fun unbindView(item: PokemonItem) {
        nameTextView.text = ""
        levelTextView.text = ""
        imageView.setImageDrawable(null)
    }

}
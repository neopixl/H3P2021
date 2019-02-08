package fr.hetic.h3p2021.recyclerview

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.ViewTarget
import com.mikepenz.fastadapter.FastAdapter
import fr.hetic.h3p2021.R


class PokemonViewHolder(itemView: View) : FastAdapter.ViewHolder<PokemonItem>(itemView) {

    val textView: TextView
    val levelTextView: TextView
    val imageView: ImageView

    var request: ViewTarget<ImageView, Drawable>? = null

    init {
        textView = itemView.findViewById(R.id.textView)
        levelTextView = itemView.findViewById(R.id.levelTextView)
        imageView = itemView.findViewById(R.id.imageView)
    }

    override fun bindView(item: PokemonItem, payloads: MutableList<Any>) {
        textView.text = item.name
        val level = item.level
        levelTextView.text = "$level"

        request = Glide.with(imageView)
            .load(item.imageUrl)
            .into(imageView)
    }

    override fun unbindView(item: PokemonItem) {
        textView.text = ""
        levelTextView.text = ""
        imageView.setImageDrawable(null)
        request?.clearOnDetach()
    }


}
package fr.hetic.h3p2021

import android.view.View
import com.mikepenz.fastadapter.IItem
import com.mikepenz.fastadapter.items.AbstractItem

data class PokemonItem(val name: String, val level: Int, val imageUrl: String): AbstractItem<PokemonItem, PokemonViewHolder>() {

    override fun getType() = 0

    override fun getLayoutRes() = R.layout.row_pokemon

    override fun getViewHolder(v: View) = PokemonViewHolder(v)

}
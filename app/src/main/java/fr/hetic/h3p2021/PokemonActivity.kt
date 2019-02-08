package fr.hetic.h3p2021

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.itemanimators.SlideInOutLeftAnimator
import kotlinx.android.synthetic.main.activity_pokemon2.*

class PokemonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon2)


        val adapterItem = ItemAdapter<PokemonItem>()
        adapterItem.add(PokemonItem("Pikachu", 10, "https://rickandmortyapi.com/api/character/avatar/123.jpeg"))
        adapterItem.add(PokemonItem("Carapuce", 15, "https://rickandmortyapi.com/api/character/avatar/5.jpeg"))
        adapterItem.add(PokemonItem("Mew", 100, "https://rickandmortyapi.com/api/character/avatar/12.jpeg"))

        for (i in 0..1000) {
            adapterItem.add(PokemonItem("Pokemon $i", i, "https://rickandmortyapi.com/api/character/avatar/$i.jpeg"))
        }


        val fastAdapter: FastAdapter<PokemonItem> = FastAdapter.with(adapterItem)

        recyclerView.adapter = fastAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        //recyclerView.layoutManager = GridLayoutManager(this, 4, RecyclerView.VERTICAL, false)
        recyclerView.itemAnimator = SlideInOutLeftAnimator(recyclerView)

        recyclerView.postDelayed(Runnable {
            adapterItem.add(1, PokemonItem("Pikachu 2", 12, "https://rickandmortyapi.com/api/character/avatar/124.jpeg"))
            fastAdapter.notifyAdapterItemInserted(1)
        }, 3000)

        fastAdapter.withOnClickListener { view, adapter, item, position ->
            Toast.makeText(PokemonActivity@this, "Pokemon click ${item.name}", Toast.LENGTH_SHORT).show()

            true
        }

    }
}

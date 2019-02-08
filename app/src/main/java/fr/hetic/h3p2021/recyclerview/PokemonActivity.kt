package fr.hetic.h3p2021.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import fr.hetic.h3p2021.R
import kotlinx.android.synthetic.main.activity_pokemon.*

class PokemonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)


        val adapter = ItemAdapter<PokemonItem>()
        adapter.add(
            PokemonItem(
                "Pikachu",
                1,
                "https://rickandmortyapi.com/api/character/avatar/361.jpeg"
            )
        )
        adapter.add(
            PokemonItem(
                "Salame",
                30,
                "https://rickandmortyapi.com/api/character/avatar/354.jpeg"
            )
        )
        adapter.add(
            PokemonItem(
                "Carapuce",
                70,
                "https://rickandmortyapi.com/api/character/avatar/172.jpeg"
            )
        )
        adapter.add(
            PokemonItem(
                "Carapuce",
                70,
                "https://rickandmortyapi.com/api/character/avatar/172.jpeg"
            )
        )
        adapter.add(
            PokemonItem(
                "Carapuce",
                70,
                "https://rickandmortyapi.com/api/character/avatar/172.jpeg"
            )
        )
        adapter.add(
            PokemonItem(
                "Carapuce",
                70,
                "https://rickandmortyapi.com/api/character/avatar/172.jpeg"
            )
        )
        adapter.add(
            PokemonItem(
                "Carapuce",
                70,
                "https://rickandmortyapi.com/api/character/avatar/172.jpeg"
            )
        )
        adapter.add(
            PokemonItem(
                "Carapuce",
                70,
                "https://rickandmortyapi.com/api/character/avatar/172.jpeg"
            )
        )
        adapter.add(
            PokemonItem(
                "Carapuce",
                70,
                "https://rickandmortyapi.com/api/character/avatar/172.jpeg"
            )
        )
        adapter.add(
            PokemonItem(
                "Carapuce",
                70,
                "https://rickandmortyapi.com/api/character/avatar/172.jpeg"
            )
        )
        adapter.add(
            PokemonItem(
                "Carapuce",
                70,
                "https://rickandmortyapi.com/api/character/avatar/172.jpeg"
            )
        )
        adapter.add(
            PokemonItem(
                "Carapuce",
                70,
                "https://rickandmortyapi.com/api/character/avatar/172.jpeg"
            )
        )
        adapter.add(
            PokemonItem(
                "Carapuce",
                70,
                "https://rickandmortyapi.com/api/character/avatar/172.jpeg"
            )
        )
        adapter.add(
            PokemonItem(
                "Carapuce",
                70,
                "https://rickandmortyapi.com/api/character/avatar/172.jpeg"
            )
        )
        adapter.add(
            PokemonItem(
                "Carapuce",
                70,
                "https://rickandmortyapi.com/api/character/avatar/172.jpeg"
            )
        )
        adapter.add(
            PokemonItem(
                "Carapuce",
                70,
                "https://rickandmortyapi.com/api/character/avatar/172.jpeg"
            )
        )
        adapter.add(
            PokemonItem(
                "Carapuce",
                70,
                "https://rickandmortyapi.com/api/character/avatar/172.jpeg"
            )
        )
        adapter.add(
            PokemonItem(
                "Carapuce",
                70,
                "https://rickandmortyapi.com/api/character/avatar/172.jpeg"
            )
        )
        adapter.add(
            PokemonItem(
                "Carapuce",
                70,
                "https://rickandmortyapi.com/api/character/avatar/172.jpeg"
            )
        )
        adapter.add(
            PokemonItem(
                "Carapuce",
                70,
                "https://rickandmortyapi.com/api/character/avatar/172.jpeg"
            )
        )
        adapter.add(
            PokemonItem(
                "Carapuce",
                70,
                "https://rickandmortyapi.com/api/character/avatar/172.jpeg"
            )
        )
        adapter.add(
            PokemonItem(
                "Carapuce",
                70,
                "https://rickandmortyapi.com/api/character/avatar/172.jpeg"
            )
        )
        adapter.add(
            PokemonItem(
                "Carapuce",
                70,
                "https://rickandmortyapi.com/api/character/avatar/172.jpeg"
            )
        )
        adapter.add(
            PokemonItem(
                "Carapuce",
                70,
                "https://rickandmortyapi.com/api/character/avatar/172.jpeg"
            )
        )
        adapter.add(
            PokemonItem(
                "Carapuce",
                70,
                "https://rickandmortyapi.com/api/character/avatar/172.jpeg"
            )
        )
        adapter.add(
            PokemonItem(
                "Carapuce",
                70,
                "https://rickandmortyapi.com/api/character/avatar/172.jpeg"
            )
        )
        adapter.add(
            PokemonItem(
                "Carapuce",
                70,
                "https://rickandmortyapi.com/api/character/avatar/172.jpeg"
            )
        )
        adapter.add(
            PokemonItem(
                "Carapuce",
                70,
                "https://rickandmortyapi.com/api/character/avatar/172.jpeg"
            )
        )
        adapter.add(
            PokemonItem(
                "Carapuce",
                70,
                "https://rickandmortyapi.com/api/character/avatar/172.jpeg"
            )
        )
        adapter.add(
            PokemonItem(
                "Carapuce",
                70,
                "https://rickandmortyapi.com/api/character/avatar/172.jpeg"
            )
        )
        adapter.add(
            PokemonItem(
                "Carapuce",
                70,
                "https://rickandmortyapi.com/api/character/avatar/172.jpeg"
            )
        )

        for (index in 1..1000) {
            adapter.add(
                PokemonItem(
                    "Test $index",
                    index,
                    "https://rickandmortyapi.com/api/character/avatar/$index.jpeg"
                )
            )
        }

        val fastAdapter: FastAdapter<PokemonItem> = FastAdapter.with(adapter)

        recyclerView.adapter = fastAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        //recyclerView.layoutManager = GridLayoutManager(this, 4, RecyclerView.VERTICAL, false)


        fastAdapter.withOnClickListener { view, adapter, item, position ->
            val name = item.name
            Toast.makeText(PokemonActivity@this, "Click sur le pokemon $name", Toast.LENGTH_SHORT).show()
            true
        }
    }
}

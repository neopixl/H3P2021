package fr.hetic.h3p2021.rickmorty

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RickMortyClient() {

    val api: RickMortyApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        api = retrofit.create(RickMortyApi::class.java)
    }
}
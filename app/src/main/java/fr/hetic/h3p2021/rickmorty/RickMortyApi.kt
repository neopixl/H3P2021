package fr.hetic.h3p2021.rickmorty

import fr.hetic.h3p2021.rickmorty.modelApi.Result
import fr.hetic.h3p2021.rickmorty.modelApi.RickMortyWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RickMortyApi {

    @GET("api/character/")
    fun getAllCharacters(): Call<RickMortyWrapper>

    @GET("api/character/{id}")
    fun getCharacter(
        @Path("id") id: Int
    ): Call<Result>
}
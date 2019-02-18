package fr.hetic.h3p2021.api

import fr.hetic.h3p2021.modelApi.RickMortyWrapper
import fr.hetic.h3p2021.modelApi.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RickMortyApi {

    @GET("character/")
    fun getAllCharacter(): Call<RickMortyWrapper>

    @GET("character/{id}")
    fun getCharacterForId(
        @Path("id") id: Int
    ): Call<Result>
}
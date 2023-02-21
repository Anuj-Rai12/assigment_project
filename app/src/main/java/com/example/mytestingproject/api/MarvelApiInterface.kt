package com.example.mytestingproject.api

import com.example.mytestingproject.model.MarvelHeroes
import com.example.mytestingproject.utils.HelperUtils
import retrofit2.Response
import retrofit2.http.GET

interface MarvelApiInterface {

    @GET(HelperUtils.EndPointUrl)
    suspend fun getMarvelHeroes(): Response<MarvelHeroes>
}
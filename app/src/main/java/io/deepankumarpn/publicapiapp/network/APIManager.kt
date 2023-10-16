package io.deepankumarpn.publicapiapp.network

import io.deepankumarpn.publicapiapp.data.remote.ChuckNorrisJoke
import retrofit2.http.GET

interface APIManager {
    @GET("random")
    suspend fun getRandomJoke(): ChuckNorrisJoke
}
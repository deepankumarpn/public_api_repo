package io.deepankumarpn.publicapiapp.ui

import io.deepankumarpn.publicapiapp.data.remote.ChuckNorrisJoke
import io.deepankumarpn.publicapiapp.network.APIManager


class MainRepository(private val apiService: APIManager) {
    suspend fun getRandomJoke(): ChuckNorrisJoke {
        return apiService.getRandomJoke()
    }

}
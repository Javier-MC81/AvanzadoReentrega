package com.jmoreno.avanzado.data

import com.jmoreno.avanzado.data.local.model.LocalSuperhero
import com.jmoreno.avanzado.data.remote.request.Locations
import com.jmoreno.avanzado.ui.model.Superhero
import javax.inject.Inject

class FakeRepository @Inject constructor(): Repository {
    override suspend fun getHeros(token: String): List<Superhero> {
        TODO("Not yet implemented")
    }

    override suspend fun doLogin(credential: String): String {
        return "1234567890"
    }

    override suspend fun getHero(id: String): LocalSuperhero {
        TODO("Not yet implemented")
    }

    override suspend fun changeFav(id: String, hero: LocalSuperhero) {
        TODO("Not yet implemented")
    }

    override suspend fun getLocations(id: String): List<Locations> {
        TODO("Not yet implemented")
    }
}
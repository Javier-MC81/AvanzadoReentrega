package com.jmoreno.avanzado.data

import com.jmoreno.avanzado.data.local.model.LocalSuperhero
import com.jmoreno.avanzado.data.remote.request.Locations
import com.jmoreno.avanzado.ui.model.Superhero

interface Repository {
    suspend fun getHeros(): List<Superhero>

    suspend fun doLogin(credentials: String): String

    suspend fun getHero(id: String): LocalSuperhero

    suspend fun changeFav(id: String, hero:LocalSuperhero)

    suspend fun getLocations(id: String): List<Locations>
}

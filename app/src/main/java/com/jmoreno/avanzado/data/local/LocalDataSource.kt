package com.jmoreno.avanzado.data.local

import com.jmoreno.avanzado.data.local.model.LocalSuperhero
import com.jmoreno.avanzado.ui.model.Superhero

interface LocalDataSource {

    suspend fun getHeros(): List<LocalSuperhero>

    suspend fun insertHeros(localSuperhero: List<LocalSuperhero>)

    suspend fun getHero(id: String): LocalSuperhero

    suspend fun updateHero(hero: LocalSuperhero)
}
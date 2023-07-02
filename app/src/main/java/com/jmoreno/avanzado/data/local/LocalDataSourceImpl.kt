package com.jmoreno.avanzado.data.local


import com.jmoreno.avanzado.data.local.SuperheroDAO.SuperheroDAO
import com.jmoreno.avanzado.data.local.model.LocalSuperhero
import com.jmoreno.avanzado.ui.model.Superhero
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val dao : SuperheroDAO) : LocalDataSource {

    override suspend fun getHeros(): List<LocalSuperhero>{
        return dao.getAll()
    }

    override suspend fun insertHeros(localSuperhero: List<LocalSuperhero>){
        return dao.insertAllList(localSuperhero)
    }

    override suspend fun getHero(id: String): LocalSuperhero { // Función necesaria para recuperar el héroe en el detalle a través de su id
        return dao.getHero(id)
    }

    override suspend fun updateHero(hero: LocalSuperhero) { // Función necesaria para actualizar el estado del atributo favorito del héroe y que el DAO reemplace el héroe
        return dao.insertHero(hero)
    }

}
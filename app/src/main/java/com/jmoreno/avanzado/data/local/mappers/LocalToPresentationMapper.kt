package com.jmoreno.avanzado.data.local.mappers

import com.jmoreno.avanzado.data.local.model.LocalSuperhero
import com.jmoreno.avanzado.ui.model.Superhero
import javax.inject.Inject


class LocalToPresentationMapper @Inject constructor(){

    fun mapLocalSuperheros(localSuperheros: List<LocalSuperhero>): List<Superhero> {
        return localSuperheros.map { mapLocalSuperheros(it) }
    }

    fun mapLocalSuperheros(getHerosResponse: LocalSuperhero): Superhero {
        return Superhero(getHerosResponse.name, getHerosResponse.id, getHerosResponse.photo,getHerosResponse.favorite,getHerosResponse.description)
    }
}

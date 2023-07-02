package com.jmoreno.avanzado.data.local.mappers

import com.jmoreno.avanzado.data.local.model.LocalSuperhero
import com.jmoreno.avanzado.data.remote.request.GetHerosResponse
import javax.inject.Inject


class RemoteToLocalMapper @Inject constructor(){

    fun mapGetHerosResponses(getHerosResponses: List<GetHerosResponse>): List<LocalSuperhero> {
        return getHerosResponses.map { mapGetHeroResponse(it) }
    }

    fun mapGetHeroResponse(getHerosResponse: GetHerosResponse): LocalSuperhero {
        return LocalSuperhero(getHerosResponse.id, getHerosResponse.name,  getHerosResponse.photo,getHerosResponse.favorite,getHerosResponse.description)
    }
}

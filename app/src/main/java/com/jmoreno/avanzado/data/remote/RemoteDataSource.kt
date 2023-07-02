package com.jmoreno.avanzado.data.remote

import com.jmoreno.avanzado.data.remote.request.GetHerosRequestBody
import com.jmoreno.avanzado.data.remote.request.GetHerosResponse
import com.jmoreno.avanzado.data.remote.request.Locations

interface RemoteDataSource {

    suspend fun getHeros(): List<GetHerosResponse>
    suspend fun doLogin(token: String): String
    suspend fun changeFav(id: String)
    suspend fun getLocations(id: String): List<Locations>

}
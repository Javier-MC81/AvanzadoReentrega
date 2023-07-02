package com.jmoreno.avanzado.data.remote

import android.content.Context
import android.content.SharedPreferences
import com.jmoreno.avanzado.data.remote.request.ChangeFavorite
import com.jmoreno.avanzado.data.remote.request.GetHerosRequestBody
import com.jmoreno.avanzado.data.remote.request.GetHerosResponse
import com.jmoreno.avanzado.data.remote.request.GetLocations
import com.jmoreno.avanzado.data.remote.request.Locations
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourceImpl @Inject constructor(private val api: DragonBallApi) : RemoteDataSource {

    private var token = ""

    override suspend fun getHeros(): List<GetHerosResponse>{

        return api.getHeros("Bearer $token",GetHerosRequestBody())
    }

    override suspend fun doLogin(token: String): String{
        this.token = api.doLogin(token)//Guardamos el token para todas las llamadas ue haya que hacer
        return token
    }

    override suspend fun changeFav(id: String){
        api.changeFav("Bearer $token",ChangeFavorite(id))
    }

    override suspend fun getLocations(id: String): List<Locations> {

        return api.getLocations("Bearer $token", GetLocations(id))
    }
}
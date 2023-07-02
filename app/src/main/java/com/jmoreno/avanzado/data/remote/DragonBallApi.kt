package com.jmoreno.avanzado.data.remote

import com.jmoreno.avanzado.data.remote.request.ChangeFavorite
import com.jmoreno.avanzado.data.remote.request.GetHerosRequestBody
import com.jmoreno.avanzado.data.remote.request.GetHerosResponse
import com.jmoreno.avanzado.data.remote.request.GetLocations
import com.jmoreno.avanzado.data.remote.request.Locations
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST


public interface DragonBallApi {

    @POST("api/auth/login")
    suspend fun doLogin(@Header("Authorization") authorization: String): String

    @POST("api/heros/all")
    suspend fun getHeros(@Header("Authorization") authorization: String,@Body getHerosRequestBody: GetHerosRequestBody): List<GetHerosResponse>

    @POST("api/data/herolike")
    suspend fun changeFav(@Header("Authorization") authorization: String,@Body superheroId: ChangeFavorite)

    @POST("api/heros/locations")
    suspend fun getLocations(@Header("Authorization") authorization: String,@Body superheroId: GetLocations): List<Locations>
}


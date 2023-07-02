package com.jmoreno.avanzado.data


import androidx.lifecycle.ViewModel
import com.jmoreno.avanzado.data.local.LocalDataSourceImpl
import com.jmoreno.avanzado.data.local.mappers.LocalToPresentationMapper
import com.jmoreno.avanzado.data.local.mappers.RemoteToLocalMapper
import com.jmoreno.avanzado.data.local.model.LocalSuperhero
import com.jmoreno.avanzado.data.remote.RemoteDataSourceImpl
import com.jmoreno.avanzado.data.remote.request.GetLocations
import com.jmoreno.avanzado.data.remote.request.Locations
import com.jmoreno.avanzado.ui.LoginViewModel
import com.jmoreno.avanzado.ui.model.Superhero
import kotlin.coroutines.coroutineContext


class RepositoryImpl (private val remoteDataSource: RemoteDataSourceImpl,
                      private val localDataSource: LocalDataSourceImpl,
                      private val remoteToLocalMapper: RemoteToLocalMapper,
                      private val localToPresentationMapper: LocalToPresentationMapper,
): Repository {



    override suspend fun getHeros(): List<Superhero>{
        if(localDataSource.getHeros().isEmpty()){
            val remoteSuperheros = remoteDataSource.getHeros()
            localDataSource.insertHeros(remoteToLocalMapper.mapGetHerosResponses(remoteSuperheros))
        }
        return localToPresentationMapper.mapLocalSuperheros(localDataSource.getHeros())
    }

    override suspend fun doLogin(credentials: String): String {
        return remoteDataSource.doLogin(credentials)
    }

    override suspend fun getHero(id: String): LocalSuperhero{
        return localDataSource.getHero(id)
    }

    override suspend fun changeFav(id: String,hero: LocalSuperhero){
        remoteDataSource.changeFav(id)
        localDataSource.updateHero(hero)
    }

    override suspend fun getLocations(id: String): List<Locations>{
        return remoteDataSource.getLocations(id)
    }

}
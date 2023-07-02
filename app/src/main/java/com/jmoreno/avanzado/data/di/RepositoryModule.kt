package com.jmoreno.avanzado.data.di

import com.jmoreno.avanzado.data.Repository
import com.jmoreno.avanzado.data.RepositoryImpl
import com.jmoreno.avanzado.data.local.LocalDataSource
import com.jmoreno.avanzado.data.local.LocalDataSourceImpl
import com.jmoreno.avanzado.data.local.mappers.LocalToPresentationMapper
import com.jmoreno.avanzado.data.local.mappers.RemoteToLocalMapper
import com.jmoreno.avanzado.data.remote.RemoteDataSource
import com.jmoreno.avanzado.data.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsRepository(repositoryImpl: RepositoryImpl): Repository

   @Binds
    abstract fun bindsLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource

    @Binds
    abstract fun bindsRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource
    companion object {
        @Provides
        fun providesRepository(
            localDataSource: LocalDataSourceImpl,
            remoteDataSource: RemoteDataSourceImpl,
            remoteToLocalMapper: RemoteToLocalMapper,
            localToPresentationMapper: LocalToPresentationMapper
        ): RepositoryImpl {
            return RepositoryImpl(
                remoteDataSource,
                localDataSource,
                remoteToLocalMapper,
                localToPresentationMapper
            )
        }
    }



}


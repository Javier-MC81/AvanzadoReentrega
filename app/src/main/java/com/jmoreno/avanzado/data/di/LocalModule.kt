package com.jmoreno.avanzado.data.di

import android.content.Context
import androidx.room.Room
import com.jmoreno.avanzado.data.local.SuperheroDAO.SuperheroDAO
import com.jmoreno.avanzado.data.local.SuperheroDAO.SuperheroDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    fun providesSuperheroDatabase(@ApplicationContext context: Context): SuperheroDataBase {
        val db = Room.databaseBuilder(
            context,
            SuperheroDataBase::class.java, "superhero-db"
        ).build()
        return db
    }

    @Provides
    fun providesDao(db: SuperheroDataBase): SuperheroDAO {
        val dao = db.superheroDao()
        return dao
    }

}

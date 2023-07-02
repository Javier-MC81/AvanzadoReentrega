package com.jmoreno.avanzado.data.local.SuperheroDAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jmoreno.avanzado.data.local.model.LocalSuperhero
import com.jmoreno.avanzado.ui.model.Superhero

@Dao
interface SuperheroDAO {
    @Query("SELECT * FROM superheros")
    suspend fun getAll(): List<LocalSuperhero>

    @Query("SELECT * FROM superheros WHERE id LIKE :id")
    fun getHero(id:String): LocalSuperhero

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHero(users: LocalSuperhero)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg users: LocalSuperhero)

    @Insert
    suspend fun insertAllList(users: List<LocalSuperhero>)

    @Delete
    suspend fun delete(user: LocalSuperhero)
}
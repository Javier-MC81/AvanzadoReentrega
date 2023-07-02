package com.jmoreno.avanzado.data.local.SuperheroDAO

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jmoreno.avanzado.data.local.model.LocalSuperhero

@Database(entities = [LocalSuperhero::class], version = 1)
abstract class SuperheroDataBase : RoomDatabase() {
    abstract fun superheroDao(): SuperheroDAO
}
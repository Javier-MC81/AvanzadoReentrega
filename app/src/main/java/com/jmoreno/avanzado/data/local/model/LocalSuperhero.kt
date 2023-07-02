package com.jmoreno.avanzado.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "superheros")
data class LocalSuperhero (
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo (name = "name") val name: String,
    @ColumnInfo(name = "photo") val photo: String,
    @ColumnInfo(name = "favorite") var favorite: Boolean,
    @ColumnInfo(name = "description") val description: String,
)
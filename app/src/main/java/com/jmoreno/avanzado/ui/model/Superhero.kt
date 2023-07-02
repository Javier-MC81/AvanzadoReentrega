package com.jmoreno.avanzado.ui.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey


data class Superhero (
    val name: String,
    val id: String,
    val photo: String,
    val favorite: Boolean,
    val description: String,
)
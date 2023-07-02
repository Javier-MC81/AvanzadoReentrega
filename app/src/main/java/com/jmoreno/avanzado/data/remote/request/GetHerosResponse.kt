package com.jmoreno.avanzado.data.remote.request

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

data class GetHerosResponse (
    @Json(name = "id") val id: String,
    @Json(name = "description") val description: String,
    @Json(name = "name") val name: String,
    @Json(name = "photo") val photo:String,
    @Json(name = "favorite") val favorite: Boolean,
    )


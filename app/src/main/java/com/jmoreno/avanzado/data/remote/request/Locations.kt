package com.jmoreno.avanzado.data.remote.request

data class Locations(
    val dateShow: String,
    val hero: Hero,
    val latitud: String,
    val id: String,
    val longitud: String,
)

data class Hero(
    val id: String,
)
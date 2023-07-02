package com.jmoreno.avanzado.utils

import com.jmoreno.avanzado.data.remote.request.Hero
import com.jmoreno.avanzado.data.remote.request.Locations
import java.util.Base64


fun generateLocations(size: Int, id: String): List<Locations> {
    val hero = generateHero()
    return (0 until size).map { Locations("", hero,"","","") }
}


fun generateHero(): Hero {
    return Hero("101")
}

fun generateCredentials(name: String, password: String): String {
    val joined = "$name+$password "
    return Base64.getEncoder().encodeToString(joined.toByteArray())
}

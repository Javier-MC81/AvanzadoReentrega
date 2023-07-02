package com.jmoreno.avanzado.ui

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmoreno.avanzado.data.Repository
import com.jmoreno.avanzado.data.RepositoryImpl
import com.jmoreno.avanzado.data.local.model.LocalSuperhero
import com.jmoreno.avanzado.data.remote.request.Locations
import com.jmoreno.avanzado.ui.model.Superhero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SuperheroViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _heros = MutableLiveData<List<Superhero>>()
    val heros: LiveData<List<Superhero>> get() = _heros

    private val _hero = MutableLiveData<LocalSuperhero>(LocalSuperhero("","","",false,""))
    val hero: LiveData<LocalSuperhero> get() = _hero

    private val _locations = MutableLiveData<List<Locations>>()
    val locations: LiveData<List<Locations>> get() = _locations

    fun getHeros(){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getHeros()
            }
            _heros.value = result//Recibimos los héroes para pintarlos en la lista
        }
    }
    fun findHero(id: String){
        viewModelScope.launch {
            val hero = withContext(Dispatchers.IO){
                repository.getHero(id)
            }
            _hero.value = hero //Buscamos los datos de un heroe en la BBDD a través de su id y los traemos de vuelta para poder pntar sus datos en la pantalla de detalle
        }
    }
    fun changeFavorite(id: String,hero: LocalSuperhero){
        viewModelScope.launch {
                repository.changeFav(id,hero)// Cambiamos el estado del atributo favorito del héroe en la BBDD
        }
    }

    fun getLocations(id: String){
        viewModelScope.launch {
            val locations = withContext(Dispatchers.IO){
                repository.getLocations(id)
            }
            _locations.value = locations//Recibimos los datos de las localizaciones del héroe para meterlos en el fragment del detalle
        }
    }
}
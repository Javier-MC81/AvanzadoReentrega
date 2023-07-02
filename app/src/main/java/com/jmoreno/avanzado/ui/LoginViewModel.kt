package com.jmoreno.avanzado.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmoreno.avanzado.data.Repository
import com.jmoreno.avanzado.data.local.model.LocalSuperhero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: Repository): ViewModel() {


    private val _token = MutableLiveData<String>()
    val token: LiveData<String> get() = _token

    fun getLogin(credential: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.doLogin(credential)
            }
            _token.value = result
        }
    }

}

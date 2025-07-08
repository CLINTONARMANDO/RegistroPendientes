package com.clindevstu.registropendientes.ui.modules.splashprincipal

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashPrincipalViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    private val _state = MutableStateFlow<SplashPrincipalState>(SplashPrincipalState.Init)
    val state: StateFlow<SplashPrincipalState> = _state

    init {
        startSplash()
    }

    private fun startSplash() {
        _state.value = SplashPrincipalState.Loading

        viewModelScope.launch {
            // Simular carga
            delay(3000)

            // Ejemplo: leer algo de SharedPreferences si quisieras
            val prefs = getApplication<Application>()
                .getSharedPreferences("prefs", Context.MODE_PRIVATE)
            val usuario = prefs.getString("username", null)

            if (usuario.isNullOrEmpty()) {
                _state.value = SplashPrincipalState.Success // O Error si quieres
            } else {
                _state.value = SplashPrincipalState.Success
            }
        }
    }
}

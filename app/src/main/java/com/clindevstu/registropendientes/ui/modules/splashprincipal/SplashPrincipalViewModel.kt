package com.clindevstu.registropendientes.ui.modules.splashprincipal

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clindevstu.registropendientes.data.preferences.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashPrincipalViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    private val _state = MutableStateFlow<SplashPrincipalState>(SplashPrincipalState.Init)
    val state: StateFlow<SplashPrincipalState> = _state

    private val userPreferences = UserPreferences(application)

    init {
        startSplash()
    }

    private fun startSplash() {
        _state.value = SplashPrincipalState.Loading

        viewModelScope.launch {
            // Simular splash con delay
            delay(1500)

            try {
                // Leer usuario de DataStore
                val usuario = userPreferences.userData.first()

                if (usuario.token.isNullOrEmpty()) {
                    // No hay token, navegar a Login
                    _state.value = SplashPrincipalState.GoLogin
                } else {
                    // Hay token, navegar al Panel
                    _state.value = SplashPrincipalState.Success
                }
            } catch (e: Exception) {
                _state.value = SplashPrincipalState.Error("Error leyendo usuario: ${e.localizedMessage}")
            }
        }
    }
}

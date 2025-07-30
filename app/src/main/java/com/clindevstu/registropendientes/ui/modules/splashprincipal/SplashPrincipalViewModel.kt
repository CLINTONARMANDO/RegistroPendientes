package com.clindevstu.registropendientes.ui.modules.splashprincipal

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clindevstu.registropendientes.data.preferences.UserPreferences
import com.clindevstu.registropendientes.domain.usecase.AppDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashPrincipalViewModel @Inject constructor(
    private val useCase: AppDataUseCase,
    application: Application
) : AndroidViewModel(application) {

    private val _state = MutableStateFlow<SplashPrincipalState>(SplashPrincipalState.Init)
    val state: StateFlow<SplashPrincipalState> = _state

    private val userPreferences = UserPreferences(application)

    private val context = application.applicationContext

    init {
        startSplash()
    }

    private fun getCurrentAppVersion(): Double {
        val versionName = context.packageManager
            .getPackageInfo(context.packageName, 0)
            .versionName

        return versionName?.toDoubleOrNull() ?: 0.0
    }

    private fun startSplash() {
        _state.value = SplashPrincipalState.Loading

        viewModelScope.launch {
            delay(1500)

            try {
                val versionResponse = useCase()
                val versionData = versionResponse.data

                val localVersion = getCurrentAppVersion()

                Log.d("AppVersion", "Versión actual: $localVersion")
                Log.d("ServerVersion", "Versión permitida: ${versionData?.ultimaVersionPermitida}")
                // Verificación de versiones
                when {
                    localVersion < versionData!!.ultimaVersionPermitida -> {
                        _state.value = SplashPrincipalState.UpdateRequired(force = true)
                        return@launch
                    }
                    localVersion > versionData.ultimaVersion -> {
                        _state.value = SplashPrincipalState.UpdateRequired(force = false)
                        return@launch
                    }
                }

                // Continuar con la lógica normal
                val usuario = userPreferences.userData.first()

                if (usuario.token.isNullOrEmpty()) {
                    _state.value = SplashPrincipalState.GoLogin
                } else {
                    _state.value = SplashPrincipalState.Success
                }

            } catch (e: Exception) {
                _state.value = SplashPrincipalState.Error("Error: ${e.localizedMessage}")
            }
        }
    }
}

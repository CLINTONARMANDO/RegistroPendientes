package com.clindevstu.registropendientes.ui.modules.panelcentral

import android.app.Application
import android.content.Context
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clindevstu.registropendientes.core.models.responses.UsuarioResponse
import com.clindevstu.registropendientes.data.preferences.UserPreferences
import com.clindevstu.registropendientes.ui.modules.splashprincipal.SplashPrincipalState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PanelCentralViewModel @Inject constructor(
) : ViewModel() {


    private val _state = MutableStateFlow<PanelCentralState>(PanelCentralState.Init)
    val state: StateFlow<PanelCentralState> = _state

    private val _isUsuarioDialogActive = MutableStateFlow<Boolean>(false)
    val isUsuarioDialogActive: StateFlow<Boolean> = _isUsuarioDialogActive

    private val _usuario = MutableStateFlow<UsuarioResponse>(UsuarioResponse())
    val usuario: StateFlow<UsuarioResponse> = _usuario.asStateFlow()

    private val _userPreferences = MutableStateFlow<UserPreferences?>(null)
    val userPreferences: StateFlow<UserPreferences?> = _userPreferences.asStateFlow()

    fun obtenerContexto(context: Context){

        _userPreferences.value =  UserPreferences(context)
        viewModelScope.launch {
            _userPreferences.value?.userData?.collect { user ->
                _usuario.value = user
            }
        }
    }

    fun onUsuarioDialogChange(value: Boolean) {
        _isUsuarioDialogActive.value = value
    }
}
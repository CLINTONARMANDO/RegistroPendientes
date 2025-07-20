package com.clindevstu.registropendientes.ui.modules.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clindevstu.registropendientes.core.models.responses.ErrorResponse
import com.clindevstu.registropendientes.core.models.responses.UsuarioResponse
import com.clindevstu.registropendientes.data.preferences.UserPreferences
import com.clindevstu.registropendientes.domain.usecase.LoginUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val userPreferences: UserPreferences
) : ViewModel() {


    private val _state = MutableStateFlow<LoginState>(LoginState.Init)
    val state: StateFlow<LoginState> = _state

    private val _usuario = MutableStateFlow<String?>(null)
    val usuario: StateFlow<String?> = _usuario

    private val _password = MutableStateFlow<String?>(null)
    val password: StateFlow<String?> = _password

    private val _usuarioError = MutableStateFlow<String?>(null)
    val usuarioError: StateFlow<String?> = _usuarioError

    private val _passwordError = MutableStateFlow<String?>(null)
    val passwordError: StateFlow<String?> = _passwordError

    private val _loginResponse = MutableStateFlow<ErrorResponse<UsuarioResponse>?>(null)
    val loginResponse: StateFlow<ErrorResponse<UsuarioResponse>?> = _loginResponse

    fun login(context: Context) {
        viewModelScope.launch {
            _state.value = LoginState.Loading // << Nuevo: muestra loader

            val user = usuario.value
            val pass = password.value

            if (!user.isNullOrBlank() && !pass.isNullOrBlank()) {
                try {
                    val errorResponse = loginUseCase(user, pass)
                    _loginResponse.value = errorResponse

                    Log.d("LOGIN_DEBUG", errorResponse.toString())

                    if (errorResponse.error == 0 && errorResponse.data != null) {
                        userPreferences.saveUser(errorResponse.data)
                        _state.value = LoginState.Success
                    } else {
                        _state.value = LoginState.Error(errorResponse.mensaje)
                    }

                } catch (e: Exception) {
                    Log.e("LOGIN_DEBUG", "Excepción: ${e.message}", e)
                    _state.value = LoginState.Error("Error inesperado: ${e.message}")
                }
            } else {
                if (user.isNullOrBlank()) validarUsuarioError()
                if (pass.isNullOrBlank()) validarPasswordError()
                _state.value = LoginState.Error("Usuario y contraseña requeridos")
            }
        }
    }

    fun onUsuarioChange(value: String) {
        _usuario.value = value
    }

    fun onPasswordChange(value: String) {
        _password.value = value
    }

    fun validarUsuarioError() {
        _usuarioError.value = if (_usuario.value.isNullOrBlank()) "El usuario es obligatorio" else null
    }

    fun validarPasswordError() {
        _passwordError.value = if (_password.value.isNullOrBlank()) "La contraseña es obligatorio" else null
    }
}
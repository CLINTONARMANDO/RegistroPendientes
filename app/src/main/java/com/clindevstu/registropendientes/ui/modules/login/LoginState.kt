package com.clindevstu.registropendientes.ui.modules.login

import com.clindevstu.registropendientes.core.models.responses.UsuarioResponse

sealed class LoginState {

    object Init : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}
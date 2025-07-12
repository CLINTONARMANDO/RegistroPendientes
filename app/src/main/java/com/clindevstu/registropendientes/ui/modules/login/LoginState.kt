package com.clindevstu.registropendientes.ui.modules.login

sealed class LoginState {

    object Init : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}
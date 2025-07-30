package com.clindevstu.registropendientes.ui.modules.registrointernet.registrointernetdetalle

sealed class RegistroInternetDetalleState {
    object Init : RegistroInternetDetalleState()
    object Loading : RegistroInternetDetalleState()
    object Success : RegistroInternetDetalleState()
    data class Error(val message: String) : RegistroInternetDetalleState()
}
package com.clindevstu.registropendientes.ui.modules.registroaverias.registroaveriasdetalle

sealed class RegistroAveriasDetalleState {
    object Init : RegistroAveriasDetalleState()
    object Loading : RegistroAveriasDetalleState()
    object Success : RegistroAveriasDetalleState()
    data class Error(val message: String) : RegistroAveriasDetalleState()
}
package com.clindevstu.registropendientes.ui.modules.registrocamaras.registrocamarasdetalle

sealed class RegistroCamarasDetalleState {
    object Init : RegistroCamarasDetalleState()
    object Loading : RegistroCamarasDetalleState()
    object Success : RegistroCamarasDetalleState()
    data class Error(val message: String) : RegistroCamarasDetalleState()
}
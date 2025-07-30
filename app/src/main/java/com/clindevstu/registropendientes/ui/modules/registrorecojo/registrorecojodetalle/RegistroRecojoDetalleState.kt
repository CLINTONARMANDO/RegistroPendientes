package com.clindevstu.registropendientes.ui.modules.registrorecojo.registrorecojodetalle

sealed class RegistroRecojoDetalleState {
    object Init : RegistroRecojoDetalleState()
    object Loading : RegistroRecojoDetalleState()
    object Success : RegistroRecojoDetalleState()
    data class Error(val message: String) : RegistroRecojoDetalleState()
}
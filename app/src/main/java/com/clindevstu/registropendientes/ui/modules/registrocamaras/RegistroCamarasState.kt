package com.clindevstu.registropendientes.ui.modules.registrocamaras

sealed class RegistroCamarasState {
    object Init : RegistroCamarasState()
    object Loading : RegistroCamarasState()
    object Success : RegistroCamarasState()
    data class Error(val message: String) : RegistroCamarasState()

}
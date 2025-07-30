package com.clindevstu.registropendientes.ui.modules.registrocamaras.agregarregistrocamaras

sealed class AgregarRegistroCamarasState {
    object Init : AgregarRegistroCamarasState()
    object Loading : AgregarRegistroCamarasState()
    object Success : AgregarRegistroCamarasState()
    data class Error(val message: String) : AgregarRegistroCamarasState()
}
package com.clindevstu.registropendientes.ui.modules.registroaverias.agregarregistroaverias

sealed class AgregarRegistroAveriasState {
    object Init : AgregarRegistroAveriasState()
    object Loading : AgregarRegistroAveriasState()
    object Success : AgregarRegistroAveriasState()
    data class Error(val message: String) : AgregarRegistroAveriasState()
}
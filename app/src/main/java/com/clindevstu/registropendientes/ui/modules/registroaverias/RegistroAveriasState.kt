package com.clindevstu.registropendientes.ui.modules.registroaverias

import com.clindevstu.registropendientes.ui.modules.registrointernet.RegistroInternetState

sealed class RegistroAveriasState {
    object Init : RegistroAveriasState()
    object Loading : RegistroAveriasState()
    object Success : RegistroAveriasState()
    data class Error(val message: String) : RegistroAveriasState()
}
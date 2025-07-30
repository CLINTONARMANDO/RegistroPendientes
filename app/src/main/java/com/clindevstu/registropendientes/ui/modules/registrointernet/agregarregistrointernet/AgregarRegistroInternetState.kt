package com.clindevstu.registropendientes.ui.modules.registrointernet.agregarregistrointernet

sealed class AgregarRegistroInternetState {
    object Init : AgregarRegistroInternetState()
    object Loading : AgregarRegistroInternetState()
    object Success : AgregarRegistroInternetState()
    data class Error(val message: String) : AgregarRegistroInternetState()
}
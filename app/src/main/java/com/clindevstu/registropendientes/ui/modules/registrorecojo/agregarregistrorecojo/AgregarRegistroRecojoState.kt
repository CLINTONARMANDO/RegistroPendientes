package com.clindevstu.registropendientes.ui.modules.registrorecojo.agregarregistrorecojo

sealed class AgregarRegistroRecojoState {
    object Init : AgregarRegistroRecojoState()
    object Loading : AgregarRegistroRecojoState()
    object Success : AgregarRegistroRecojoState()
    data class Error(val message: String) : AgregarRegistroRecojoState()
}
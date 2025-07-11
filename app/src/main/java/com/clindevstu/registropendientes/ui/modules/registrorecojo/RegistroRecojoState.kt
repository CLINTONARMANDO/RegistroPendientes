package com.clindevstu.registropendientes.ui.modules.registrorecojo

sealed class RegistroRecojoState {
    object Init : RegistroRecojoState()
    object Loading : RegistroRecojoState()
    object Success : RegistroRecojoState()
    data class Error(val message: String) : RegistroRecojoState()
}
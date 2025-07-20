package com.clindevstu.registropendientes.ui.modules.panelcentral.panelprincipal

sealed class PanelPrincipalState {
    object Init : PanelPrincipalState()
    object Loading : PanelPrincipalState()
    object Success : PanelPrincipalState()
    data class Error(val message: String) : PanelPrincipalState()
}